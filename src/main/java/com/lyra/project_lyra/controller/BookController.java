package com.lyra.project_lyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.dto.PageRequestDTO;
import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor 
public class BookController {
	
	@Autowired
	private final BookService bookService;
	
	//KAKAO API 책 정보 가져오기
	@GetMapping("/insert")
	public void indextest() {
				
	}

	@PostMapping("/logintest")
	public ResponseEntity<String> logintest() {
		return ResponseEntity.ok().body("토큰 인증 완료.");
	}
	
	@PostMapping("/insert")
	public void indextest2(@RequestBody BookDTO dto) {
		log.info("dto......." + dto);
		bookService.insert(dto);
	}
	
	
	//책 컨트롤러 테스트 코드
	@GetMapping("/list")
	public void RankingList(PageRequestDTO pageRequestDTO, Model model) {
		//log.info("list.........." + pageRequestDTO);
		//model.addAttribute("result", bookService.getBookRankingList(pageRequestDTO));
	}

}
