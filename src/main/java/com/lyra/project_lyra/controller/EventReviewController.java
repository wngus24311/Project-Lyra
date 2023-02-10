package com.lyra.project_lyra.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyra.project_lyra.dto.EventReviewDTO;
import com.lyra.project_lyra.service.interfaces.EventReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class EventReviewController {
	
	private final EventReviewService eventReviewService;
	
	// 리스트를 가져오는 메소드
	@GetMapping("/{eventnum}/all")
	public ResponseEntity<List<EventReviewDTO>> eventReviewGetList(Long reviewNum) {
		log.info(" ===== GetList(Review) =====");
		log.info("EventNumber =>" + reviewNum);
		
		List<EventReviewDTO> eventReviewDTOList = eventReviewService.getEventReviewList(reviewNum);
		// 생성자 역할
		return new ResponseEntity<>(eventReviewDTOList, HttpStatus.OK);
	}
	// 이벤트 번호를 참고하여 댓글 생성하는 메소드
	@PostMapping("/{eventnum}")
	public ResponseEntity<Long> eventReviewAdd(@RequestBody EventReviewDTO eventReviewDTO){
		log.info("===== Add EventReview =====");
		log.info("EventReviewDTO =>" + eventReviewDTO);
		
		Long eventReviewNum = eventReviewService.register(eventReviewDTO);

		return new ResponseEntity<>(eventReviewNum, HttpStatus.OK);
	}
	
	// 댓글 삭제
	@PutMapping("/{eventnum}/{reviewnum}")
	public ResponseEntity<Long> eventReviewRemove(@PathVariable Long eventReviewNum){
		log.info("=====Remove EventReview =====");
		
		return new ResponseEntity<>(eventReviewNum, HttpStatus.OK);
	}
	
}
