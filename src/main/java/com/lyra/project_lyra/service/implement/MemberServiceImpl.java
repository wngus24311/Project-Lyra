package com.lyra.project_lyra.service.implement;

import com.lyra.project_lyra.dto.MemberDTO;
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
    private final MemberInfoRepository repository;

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
        repository.findByUsername(username)
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
        repository.save(memberInfo);

        return "SUCCESS";
    }

    /** Controller에서 받은 값 있는지 체크 */
    @Override
    public String login(String username, String password) {
        /** username 없음 체크 */
        MemberInfo selectedUser = repository.findByUsername(username)
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
        MemberInfo memberInfo = repository.findByUsername(username).get();
        memberInfo.setMemberGenre(memberGenre);
        repository.save(memberInfo);
    }

    @Override
    public MemberDTO check(String username) {
        MemberDTO dto = new MemberDTO();
        dto.setUsername(username);
        return dto;
    }

}
