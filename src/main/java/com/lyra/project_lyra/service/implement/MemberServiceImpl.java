package com.lyra.project_lyra.service.implement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.entity.combine.CombinePage;
import com.lyra.project_lyra.entity.member.MemberInfo;
import com.lyra.project_lyra.excpetion.AppException;
import com.lyra.project_lyra.excpetion.ErrorCode;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.interfaces.MemberService;
import com.lyra.project_lyra.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberInfoRepository memberRepository;

    // properties에 있는 secretKey
    @Value("${jwt.token.secret}")
    private String secretKey;

    /** Token의 파기 기한 설정 변수 */
    private Long expiredMs = 1000 * 60 * 60L;

    // EncoderConfig class에서 Dependency injection
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /** 회원 가입 */
    @Override
    public String join(String username, String password, int age, int gender, String nickname, String memberGerne, String subscribeState) {
        /** username 중복 체크 */
    	memberRepository.findByUsername(username)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, username + " 는 이미 있습니다.");
                });

        /** Controller에서 받은 값 DB에 저장*/
        MemberInfo memberInfo = MemberInfo.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .age(age)
                .gender(gender)
                .nickname(nickname)
                .memberGenre(memberGerne)
                .subscribeState(subscribeState)
                .lastlogin(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        memberRepository.save(memberInfo);

        return "SUCCESS";
    }

    /** Controller에서 받은 값 있는지 체크 */
    @Override
    public String login(String username, String password) {
        /** username 없음 체크 */
        MemberInfo selectedUser = memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOTFOUND, username + "이 없습니다"));

        /** password 틀림 체크 */
        if (!bCryptPasswordEncoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVAILD_PASSWORD, "패스워드가 틀렸습니다.");
        }
        String token = JwtUtil.createJwt(selectedUser.getUsername(), secretKey, expiredMs);
        log.info(token);
        return token;
    }

    /** add to memberGerne */
    @Transactional
    @Override
    public void addMemberGenre(String username, String memberGenre) {
        MemberInfo memberInfo = memberRepository.findByUsername(username).get();
        memberInfo.setMemberGenre(memberGenre);
        memberRepository.save(memberInfo);
    }

    @Override
    public MemberDTO check(String username) {
        MemberDTO dto = new MemberDTO();
        dto.setUsername(username);
        return dto;
    }


	@Override
	public List<MemberDTO> getList() {
		// 리뷰 페이지
			List<MemberInfo> members = memberRepository.findAll();
			List<MemberDTO> memberDTOList = new ArrayList<>();

			for (MemberInfo member : members) {
				MemberDTO memberDTO = MemberDTO.builder()
						.username(member.getUsername())
						.password(member.getPassword())
						.age(member.getAge())
						.gender(member.getGender())
						.nickname(member.getNickname())
						.memberGenre(member.getMemberGenre())
						.subscribeState(member.getSubscribeState())
						.lastlogin(member.getLastlogin())
						.build();

				memberDTOList.add(memberDTO);
			}

			log.info(memberDTOList);
			return memberDTOList;
	}

	@Override
	public String[] getCategory(String username) {
		String[] category;
		String[] categoryResult = new String[2];

		MemberInfo memberInfo = new MemberInfo();

		Optional<MemberInfo> memberDTO = memberRepository.findById(username);

		category = memberDTO.get().getMemberGenre().split(",");

		int[] iFlag = new int[category.length];


		//가장 많은 데이터 구분
		for (int i = 0; i < category.length; i++) {
			for (int y = 0; y < category.length; y++) {
				if (category[i].equals(category[y])) {
					iFlag[i]++;
				}
			}
		}

		//중복되는 데이터 체크
		for (int i = 0; i < category.length; i++) {
			for (int y = 0; y < category.length; y++) {
				if (iFlag[i] == iFlag[y] && i != y && category[i].equals(category[y])) {
					iFlag[y] = 0;
				}
			}
		}

		// 최대값 초기값 세팅
        int maxOne = iFlag[0];

        // 최대값 구하기 1차
        for (int num : iFlag) {
            if (num > maxOne) {
            	maxOne = num;
            }
        }

        // 최대 값 카테고리 추가
        for (int i = 0; i < category.length; i++) {
        	if (maxOne == iFlag[i]) {
        		categoryResult[0] = category[i];
        		iFlag[i] = 0;
        		break;
        	}
        }

        int maxTwo = iFlag[0];
        log.info("iFlag" + maxTwo);

        // 최대값 구하기 2차
        for (int num : iFlag) {
            if (num > maxTwo) {
            	maxTwo = num;
            }
        }

        // 2번째 최대 값 카테고리 추가
        for (int i = 0; i < category.length; i++) {
        	if (maxTwo == iFlag[i]) {
        		categoryResult[1] = category[i];
        	}
        }

		log.info(categoryResult[0]);
		log.info(categoryResult[1]);

		return categoryResult;
	}

	@Override
	public void categoryInsert(String username, String category) {
		MemberInfo memberInfo = new MemberInfo();
		String getMemberGerne = memberRepository.findCategory(username);
		String categorySum = "";


		if (category.equals("")) {
			categorySum = getMemberGerne + category;
		} else {
			categorySum = getMemberGerne + "," + category;
		}

		memberRepository.updateCategory(username, categorySum);
	}

	@Override
	public MemberDTO getUsernameInfo(String username) {
		MemberInfo member = new MemberInfo();
		MemberDTO memberDTO = new MemberDTO();

		member = memberRepository.findUsernameInfo(username);

		memberDTO = memberDTO.builder()
				.username(member.getUsername())
				.age(member.getAge())
				.gender(member.getGender())
				.nickname(member.getNickname())
				.build();

		log.info("findUsernameInfo" + memberDTO);
		return memberDTO;
	}

	@Override
	public void updateMembership(String membership, String username) {
		memberRepository.updateMembership(membership, username);
	}

	@Override
	public String getMembership(String username) {
		MemberInfo memberInfo = memberRepository.getById(username);

		String membership = memberInfo.getSubscribeState();

		return membership;
	}
}
