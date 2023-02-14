package com.lyra.project_lyra.service.implement;

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

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberInfoRepository repository;

    // properties에 있는 secretKey
    @Value("${jwt.secret}")
    private String secretKey;

    // Token의 파기 기한 설정 변수
    private Long expiredMs = 1000 * 60 * 60L;

    // EncoderConfig class에서 Dependency injection
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 가입
    @Override
    public String join(String username, String password) {
        // username 중복 체크
        repository.findByUsername(username)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, username + " 는 이미 있습니다.");
                });

        // Controller에서 받은 값 DB에 저장
        MemberInfo memberInfo = MemberInfo.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
        repository.save(memberInfo);

        return "SUCCESS";
    }

    /** Controller에서 받은 값 중복 체크 한번 더 */
    @Override
    public String login(String username, String password) {
        // username & password 중복 체크
        repository.findByUsernameAndPassword(username, password)
                .ifPresent(user -> {
                    bCryptPasswordEncoder.matches(password, user.getPassword());
                    log.info("로그인이 확인 되었습니다.");
                });
        return JwtUtil.createJwt(username, secretKey, expiredMs);
    }
}
