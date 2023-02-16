package com.lyra.project_lyra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("/insert")
	public void indextest2(@RequestBody BookDTO dto) {
		log.info("dto......." + dto);
		bookService.insert(dto);
	}
	
	
	//책 컨트롤러
	@GetMapping("/list")
	public void RankingList(PageRequestDTO pageRequestDTO, Model model) {
		//log.info("list.........." + pageRequestDTO);
		//model.addAttribute("result", bookService.getBookRankingList(pageRequestDTO));
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//책 리뷰 컨트롤러
	@GetMapping(value="/book/{bookNum}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<BookDTO>> getListByBoard(@PathVariable("bookNum") Long bookNum) {
//	
//		log.info("bookNum : " + bookNum);
//		
//		return new ResponseEntity<>(bookService.getList(bookNum), HttpStatus.OK);
//	}
	
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody BookDTO bookDTO){
		log.info("controller......." + bookDTO);
		Long reviewNum = bookService.register2(bookDTO);
		return new ResponseEntity<Long>(reviewNum, HttpStatus.OK);
	}
	
	@DeleteMapping("/{reviewNum}")
	public ResponseEntity<String> remove(@PathVariable("reviewNum") Long reviewNum){
		
		log.info("reviewNum : " + reviewNum);
		
		bookService.remove(reviewNum);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/{reviewNum}")
	public ResponseEntity<String> modify(@RequestBody BookDTO bookDTO){
		log.info("BookDTO" + bookDTO);
		bookService.modify(bookDTO);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
