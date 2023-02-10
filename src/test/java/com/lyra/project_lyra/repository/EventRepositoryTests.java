package com.lyra.project_lyra.repository;

import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.entity.event.EventImage;
import com.lyra.project_lyra.entity.event.EventReview;
import com.lyra.project_lyra.repository.event.EventImageRepository;
import com.lyra.project_lyra.repository.event.EventRepository;
import com.lyra.project_lyra.repository.event.EventReviewRepository;

@SpringBootTest
public class EventRepositoryTests {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventImageRepository eventImageRepository;
	
	@Autowired
	private EventReviewRepository eventReviewRepository;
	
	
	// 컬럼 생성 테스트
	@Test
	public void insertEventAndReview() {
		// 이벤트 테이블 컬럼 생성
		IntStream.range(0, 20).forEach(e -> {
			Event event = Event.builder()
							   .eventname(e + "번 이벤트입니다.")
							   .eventtitle(e + "번 이벤트 게시글 제목입니다.")
							   .build();
			System.out.println("===== Insert Tests =====");
			eventRepository.save(event);
			
			// 이벤트 이미지 컬럼 생성
			EventImage eventImage = EventImage.builder()
											  .uuid(UUID.randomUUID().toString())
											  .event(event)
											  .imagename("Test Image" + e + ".jpg")
											  .path("www.org.lyra")
											  .build();
				eventImageRepository.save(eventImage);
				
			// 이벤트 페이지 댓글 컬럼 생성
			EventReview eventReview = EventReview.builder()
												 .reviewid("user" + e)
												 .event(event)
												 .reviewcontent(e + "번째 댓글입니다.")
												 .build(); 
				eventReviewRepository.save(eventReview);
		});
	}
	@Test
	public void deleteTest() {
		Long num = 15L;
		// eventRepository는 포킹키가 걸려있어 
		// 밑에 2를 먼저 삭제후에 삭제가 가능하다.
		eventRepository.deleteById(num);
//		eventImageRepository.deleteById(num);
//		eventReviewRepository.deleteById(num);
	}
	
}
