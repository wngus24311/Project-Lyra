package com.lyra.project_lyra.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import com.lyra.project_lyra.service.interfaces.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;
    private final MemberInfoRepository memberRepository;
    private final MemberService memberService;
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
        memberServiceImpl.join(dto.getUsername(),
                dto.getPassword(),
                dto.getAge(),
                dto.getGender(),
                dto.getNickname(),
                dto.getMemberGerne(),
                dto.getSubscribeState());

        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
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
        String password = memberRepository.findByUsername(dto.getUsername()).get().getPassword();
        /** PasswordEncoder의 비교 메서드인 matches로 비교하여 확인 후 OK 보냄 */
        if (bCryptPasswordEncoder.matches(dto.getPassword(), password)) {
            log.info("로그인 확인");
            return ResponseEntity.ok().body(memberServiceImpl.login(dto.getUsername(), dto.getPassword()));
        }
        log.info("로그인 실패");
        /** 아니면 badRequest로 보냄 */
        return ResponseEntity.badRequest().body("아이디가 다릅니다.");
    }
    
    @GetMapping("/mypage/{data}")
    public ModelAndView genreselection(Model model, @PathVariable("data") String loginUser) {
    	String username = loginUser;
        
        log.info("memberService getUsernameInfo" + memberService.getUsernameInfo(username));
        
        model.addAttribute("userInfo", memberService.getUsernameInfo(username));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/mypage");
        return modelAndView;
    }
    
    @GetMapping("/category")
    public ModelAndView category() {
        log.info("========= Get Genreselection ========= ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/category");
        return modelAndView;
    }
}
