package com.lyra.project_lyra.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;

// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)
public interface EventService {

	// Entity 클래스 타입으로 받도록 되어있다.
	void save(EventDTO eventDTO) throws IOException;

	// 게시글 목록보기
	List<EventDTO> findAll();

	// 조회수 보기
	void updateHits(Long evnum);

	// 게시글 상세보기
	EventDTO findById(Long evnum);

	// 게시글 수정
	EventDTO update(EventDTO eventDTO);

	// 게시글 삭제
	void delete(Long evnum);

	// 페이지 리스트
	public Page<EventDTO> paging(Pageable pageable);

	// entityToDTO (생성자에따라 연관있음)
	default EventDTO toBoardDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEvnum(event.getEvnum());
		eventDTO.setEventTitle(event.getEventTitle());
		eventDTO.setEventName(event.getEventName());
		eventDTO.setEventHits(event.getEventHits());
		eventDTO.setEventStartDate(event.getCreatedTime());
		eventDTO.setEventEndDate(event.getUpdatedTime());
		// file이 없는경우 boardDTO의 Attached값을 Entity에 담겨있는 Attached값으로 setting
		if (event.getImageAttached() == 0) {
			eventDTO.setImageAttached(event.getImageAttached()); // 0

		} else {
			eventDTO.setImageAttached(event.getImageAttached()); // 1
			// 파일 이름을 가져가야함.
			// select * from board_table b, board_file_table bf where b.id=bf.board_id
			// and where b.id=?
			// 첨부파일이 하나이기에 get(0)번 인덱스로 접근함.
			eventDTO.setEventOriginalImageName(event.getEventImageEntityList().get(0).getEventOriginalImageName());
			eventDTO.setEventServerImageName(event.getEventImageEntityList().get(0).getEventServerImageName());
		}
		
		if (event.getThumbnailAttached() == 0) {
				eventDTO.setThumbnailAttached(event.getThumbnailAttached()); // 0
			
		} else {
			eventDTO.setThumbnailAttached(event.getThumbnailAttached()); // 1
			eventDTO.setEventOriginalThumbnailName(event.getEventImageEntityList().get(0).getEventOriginalThumbnailName());
			eventDTO.setEventServerThumbnailName(event.getEventImageEntityList().get(0).getEventServerThumbnailName());
		}
		
		return eventDTO;
	}
	

	// dtoToEntity(기존 save 또는 이미지, 썸네일이 없을때사용)
	// DTO에 담긴 값들을 Entity객체로 옮겨담는 작업.
	// save.html에서 입력한 값을 BoardDTO로 담아온걸 작성자값을 entity작성자값으로 변환함.
	default Event toSaveEntity(EventDTO eventDTO) {
		Event evnet = new Event();
		
		evnet.setEventTitle(eventDTO.getEventTitle());
		evnet.setEventName(eventDTO.getEventName());
		evnet.setEventHits(0);
		evnet.setImageAttached(0); // Image가 없을때.
		evnet.setThumbnailAttached(0); // Thumbnail이 없을때.
		return evnet;
	}
	
	// dtoToEntity(업데이트시 사용)
	// DTO에서 Entity로 변환할때 Id객체도 포함시키면 Jpa가 업데이트를 하는걸로 인지.
	default Event toUpdateEntity(EventDTO eventDTO) {
		Event event = new Event();

		event.setEvnum(eventDTO.getEvnum());
		event.setEventTitle(eventDTO.getEventTitle());
		event.setEventName(eventDTO.getEventName());
		event.setEventHits(eventDTO.getEventHits());
		return event;
	}

	// dtoTOEntity(이미지와 썸네일이 있을때 사용)
	default Event toSaveFileEntity(EventDTO eventDTO) {
		Event event = new Event();

		event.setEventTitle(eventDTO.getEventTitle());
		event.setEventName(eventDTO.getEventName());
		event.setEventHits(0);
		event.setImageAttached(1); 
		event.setThumbnailAttached(1); 
		return event;
	}
}