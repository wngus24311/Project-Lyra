package com.lyra.project_lyra.controller;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Log4j2
public class MemberController {
    private final MemberServiceImpl service;
    private final MemberInfoRepository repository;
    /** EncoderConfig class에서 Dependency injection */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberDTO dto) {
        /** 회원 가입 service로 보냄 */
        service.join(dto.getUsername(), dto.getPassword());

        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO dto) {
        /** ID로 Password 찾아서 비교할 준비 */
        String password = repository.findByUsername(dto.getUsername()).get().getPassword();

        /** PasswordEncoder의 비교 메서드인 matches로 비교하여 확인 후 OK 보냄 */
        if (bCryptPasswordEncoder.matches(dto.getPassword(), password)) {
            return ResponseEntity.ok().body(service.login(dto.getUsername(), dto.getPassword()));
        }

        /** 아니면 badRequest로 보냄 */
        return ResponseEntity.badRequest().body("아이디가 다릅니다.");
    }
}
