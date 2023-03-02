package com.lyra.project_lyra.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.entity.event.EventReview;
import com.lyra.project_lyra.entity.member.MemberInfo;


// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)
public interface EventService {

	// Entity 클래스 타입으로 받도록 되어있다.
	void register(EventDTO eventDTO) throws IOException;


	// 조회수 보기
	void updateHits(Long evnum);

	// 게시글 상세보기T
	EventDTO findById(Long evnum);

	// 게시글 수정
	EventDTO update(EventDTO eventDTO);

	// 게시글 삭제
	void delete(Long evnum);
	
	// 페이지 리스트
	public Page<EventDTO> paging(Pageable pageable);
	
	// 리뷰 리스트
	List<EventDTO> reviewFindAll(Long evnum);

	// 리뷰 생성
	Long reviewRegister(EventDTO eventDTO);
	
	// 리뷰 삭제
	void reviewDelete(Long evrnum);


	default EventDTO toRegisterEntity(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEvnum(event.getEvnum());
		eventDTO.setEventTitle(event.getEventTitle());
		eventDTO.setEventName(event.getEventName());
		eventDTO.setEventHits(event.getEventHits());
		eventDTO.setEventStartDate(event.getEventStartDate());
		eventDTO.setEventEndDate(event.getEventEndDate());
		eventDTO.setEventReviewCount(event.getReviewCount());
		eventDTO.setEventOriginalImageName(event.getEventOriginalImageName());
		eventDTO.setEventServerImageName(event.getEventServerImageName());
		eventDTO.setEventOriginalThumbnailName(event.getEventOriginalThumbnailName());
		eventDTO.setEventServerThumbnailName(event.getEventServerThumbnailName());
			
		return eventDTO;
	}
	
	
	
	// dtoToEntity(업데이트시 사용)
	// DTO에서 Entity로 변환할때 Id객체도 포함시키면 Jpa가 업데이트를 하는걸로 인지.
	default Event toUpdateEntity(EventDTO eventDTO) {
		
		Event event = new Event();
		
		event.setEvnum(eventDTO.getEvnum());
		event.setEventTitle(eventDTO.getEventTitle());
		event.setEventName(eventDTO.getEventName());
		event.setEventHits(eventDTO.getEventHits());
		event.setEventStartDate(eventDTO.getEventStartDate());
		event.setEventEndDate(eventDTO.getEventEndDate());
		event.setEventOriginalImageName(eventDTO.getEventOriginalImageName());
		event.setEventServerImageName(eventDTO.getEventServerImageName());
		event.setEventOriginalThumbnailName(eventDTO.getEventOriginalThumbnailName());
		event.setEventServerThumbnailName(eventDTO.getEventServerThumbnailName());

		
		return event;
	}
	

	default EventDTO toEventReviewDTO(EventReview eventReview, Long evnum) {

		
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEvrnum(eventReview.getEvrnum());
		eventDTO.setMemberId(eventReview.getMemberInfo().getUsername());
		eventDTO.setEventReviewContent(eventReview.getEventReviewContent());
		eventDTO.setEventReviewRegdate(eventReview.getRegDate());
		eventDTO.setEvnum(evnum);
		return eventDTO;
	}
	

	default EventReview toRegisterReviewEntity(EventDTO eventDTO, Event event) {
		MemberInfo member = new MemberInfo();
		
		EventReview eventReview = new EventReview();
		member.setUsername(eventDTO.getMemberId());
		eventReview.setEventReviewContent(eventDTO.getEventReviewContent());
		eventReview.setRegDate(eventDTO.getEventReviewRegdate());
		eventReview.setEvent(event);
		return eventReview;
	}


	

}	
