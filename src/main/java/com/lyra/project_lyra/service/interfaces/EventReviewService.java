package com.lyra.project_lyra.service.interfaces;

import java.util.List;

import com.lyra.project_lyra.dto.EventReviewDTO;
import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.entity.event.EventReview;

public interface EventReviewService {
	Long register(EventReviewDTO eventReviewDTO);
	List<EventReviewDTO> getEventReviewList(Long reviewNum);
	void modify(EventReviewDTO eventReviewDTO);
	void remove(Long reviewNum);
	
	// EventReivewDTO에 있는걸 EventReview를 넣음
	default EventReview dtoToEntity(EventReviewDTO eventReviewDTO) {
		EventReview review = EventReview.builder()
										.reviewnum(eventReviewDTO.getReviewNum())
										.event(Event.builder()
													.eventnum(eventReviewDTO.getReviewNum())
													.build())
										.build();
		
		return review;
	}
	
	// EventReview에 있는걸 EventReviewDTO에 넣는 메소드
	default EventReviewDTO entityToDTO(EventReview eventReview) {
		EventReviewDTO eventReviewDTO = EventReviewDTO.builder()
													  .reviewNum(eventReview.getReviewnum())
													  .reviewId(eventReview.getReviewid())
													  .eventNum(eventReview.getEvent().getEventnum())
													  // userid 추가
													  .reviewContent(eventReview.getReviewcontent())
												/*	  .regDate(eventReview.getRegDate())
													  .updateDate(eventReview.getUpdateDate()) */
													  .build();
		
		return eventReviewDTO;
	}
}
