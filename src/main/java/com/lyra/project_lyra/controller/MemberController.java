package com.lyra.project_lyra.controller;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@RestController
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
    private final MemberServiceImpl service;
    private final MemberInfoRepository repository;
    /** EncoderConfig class에서 Dependency injection */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/join")
    public ModelAndView join() {
        log.info("========= Get Join ========= ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/join");
        return modelAndView;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberDTO dto) {
        log.info("탐");
        /** 회원 가입 service로 보냄 */
        service.join(dto.getUsername(),
                dto.getPassword(),
                dto.getAge(),
                dto.getGender(),
                dto.getNickname(),
                dto.getMemberGenre(),
                dto.getSubscribeState());
        ;

        return ResponseEntity.ok().body(service.login(dto.getUsername(), dto.getPassword()));
    }

    @GetMapping("/category")
    public ModelAndView category() {
        log.info("/member/category");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/category");
        return modelAndView;
    }

    @PostMapping("/category")
    public ResponseEntity<String> category(@RequestBody MemberDTO dto , Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        log.info("username ==========> " + username);
        service.addMemberGenre(username, dto.getMemberGenre());
        return ResponseEntity.ok().body("장르 선택이 완료 되었습니다.");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        log.info("========= Get Login ========= ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO dto, HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization =====> " + authorization);
        /** ID로 Password 찾아서 비교할 준비 */
        String password = repository.findByUsername(dto.getUsername()).get().getPassword();
        log.info("Test ==========> " + dto.getUsername());

        /** PasswordEncoder의 비교 메서드인 matches로 비교하여 확인 후 OK 보냄 */
        if (bCryptPasswordEncoder.matches(dto.getPassword(), password)) {
            log.info("로그인 확인");
            return ResponseEntity.ok().body(service.login(dto.getUsername(), dto.getPassword()));
        }
        log.info("로그인 실패");
        /** 아니면 badRequest로 보냄 */
        return ResponseEntity.badRequest().body("아이디가 다릅니다.");
    }
}
