package com.lyra.project_lyra.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

	private final BookService bookService;

	// 책 리뷰 컨트롤러
	@GetMapping(value ="/main/{bookNum}")
	public ResponseEntity<List<BookDTO>> getReviewByModal(@PathVariable("bookNum") Long bookNum) {

		log.info("ReviewController /main/bookNum :" + bookNum);

		return new ResponseEntity<>(bookService.getReviewsOfBook(bookNum), HttpStatus.OK);

	}

	@PostMapping("")
	public ResponseEntity<Object> addReview(@RequestBody BookDTO bookDTO,Authentication authentication) {

		log.info("------------ add review -------------------");
		log.info("BookDTO : " + bookDTO);

		String username = (String)authentication.getPrincipal();
		
		Long reviewnum = bookService.reviewRegister(bookDTO, username);
		Long bookNum = bookService.getReviewsOfBook(bookDTO.getBookNum()).get(0).getBookNum();
		
		log.info("리뷰넘-------------" + reviewnum);

		return ResponseEntity.ok().body(bookService.getReviewsOfBook(bookNum));	
	}

	@DeleteMapping("/{bookNum}/{reviewNum}")
	public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum) {

		log.info("--------------- remove review ----------------");
		log.info("reviewNum : " + reviewNum);

		bookService.remove(reviewNum);

		return new ResponseEntity<Long>(reviewNum, HttpStatus.OK);

	}
}