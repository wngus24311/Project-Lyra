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

	// Entity Ŭ���� Ÿ������ �޵��� �Ǿ��ִ�.
	void register(EventDTO eventDTO) throws IOException;


	// ��ȸ�� ����
	void updateHits(Long evnum);

	// �Խñ� �󼼺���T
	EventDTO findById(Long evnum);

	// �Խñ� ����
	EventDTO update(EventDTO eventDTO);

	// �Խñ� ����
	void delete(Long evnum);
	
	// ������ ����Ʈ
	public Page<EventDTO> paging(Pageable pageable);
	
	// ���� ����Ʈ
	List<EventDTO> reviewFindAll(Long evnum);

	// ���� ����
	Long reviewRegister(EventDTO eventDTO);
	
	// ���� ����
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
	
	
	
	// dtoToEntity(������Ʈ�� ���)
	// DTO���� Entity�� ��ȯ�Ҷ� Id��ü�� ���Խ�Ű�� Jpa�� ������Ʈ�� �ϴ°ɷ� ����.
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
