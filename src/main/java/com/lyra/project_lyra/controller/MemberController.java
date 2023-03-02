package com.lyra.project_lyra.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import com.lyra.project_lyra.service.interfaces.BookService;
import com.lyra.project_lyra.service.interfaces.CombineService;
import com.lyra.project_lyra.service.interfaces.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;
    private final MemberInfoRepository memberRepository;
    private final MemberService memberService;
    private final CombineService combineService;
    private final BookService bookService;
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

        return ResponseEntity.ok().body(memberServiceImpl.login(dto.getUsername(), dto.getPassword()));
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
        memberServiceImpl.addMemberGenre(username, dto.getMemberGerne());
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

    @GetMapping("/mypage")
    public ModelAndView genreselection(Model model, @RequestParam(value = "name", required=false) String loginUser) {
    	String username;
		log.info(loginUser);
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}	
        
        log.info("memberService getUsernameInfo" + memberService.getUsernameInfo(username));
        log.info("memberServie getKeepList" + combineService.getKeepList(username));
        
        model.addAttribute("membership", memberService.getMembership(username));
        model.addAttribute("page", combineService.getPage(combineService.getPageList(username)));
        model.addAttribute("pageList", bookService.getBookList(combineService.getPageList(username), combineService.bookLikeList(username), combineService.bookKeepList(username)));
        model.addAttribute("keepList", bookService.getBookList(combineService.getKeepList(username), combineService.bookLikeList(username), combineService.bookKeepList(username)));
        model.addAttribute("userInfo", memberService.getUsernameInfo(username));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/mypage");
        return modelAndView;
    }
    
    @GetMapping("/membership")
    public ModelAndView membership(Model model,@RequestParam(value = "name", required=false) String loginUser) {
    	String username;
		log.info(loginUser);
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}	
		
		model.addAttribute("membership", memberService.getMembership(username));
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/member/membership");
        return modelAndView;
    }
    
    @PostMapping("/membership")
	public String setKeepInsert(Authentication authentication) {
    	String username = (String)authentication.getPrincipal();

		return username;
	}
    
    @PostMapping("/mypage")
	public String getMypage(Authentication authentication) throws Exception{
		String username = (String)authentication.getPrincipal();

		return username;
	}
	
	@PostMapping("/memberInsert")
	public String setMemberInsert(Model model, @RequestBody Map<String,Object> data, Authentication authentication) {
    	log.info(data);
    	log.info(data.get("membership"));
    	
    	String membership = Integer.toString((Integer)data.get("membership"));
		
		String username = (String)authentication.getPrincipal();
		
		log.info(membership);
		
		memberService.updateMembership(membership, username);
		
		return username;
	}
}
