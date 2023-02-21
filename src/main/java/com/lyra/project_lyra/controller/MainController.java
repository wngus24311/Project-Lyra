package com.lyra.project_lyra.controller;

import com.lyra.project_lyra.configuration.JwtFilter;
import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.entity.member.MemberInfo;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import com.lyra.project_lyra.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/main/*")
@Log4j2
@RequiredArgsConstructor 
public class MainController {
	private final MemberServiceImpl service;
	private final MemberInfoRepository repository;
	private final BookService bookService;
	
	@GetMapping("/main")
	public void getMainList(Model model) {
		log.info("list test : " + bookService.getList());
		model.addAttribute("result", bookService.getList());
		log.info("확인쓰");
	}
	
}
