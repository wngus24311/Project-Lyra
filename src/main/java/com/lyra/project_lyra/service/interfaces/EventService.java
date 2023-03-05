package com.lyra.project_lyra.service.interfaces;

import java.io.IOException;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;


// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)
public interface EventService {

	void register(EventDTO eventDTO) throws IOException;


	void updateHits(Long evnum);

	EventDTO findById(Long evnum);

	EventDTO update(EventDTO eventDTO);

	void delete(Long evnum);
	
	public Page<EventDTO> paging(Pageable pageable);
	


	default EventDTO toRegisterEntity(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEvnum(event.getEvnum());
		eventDTO.setEventTitle(event.getEventTitle());
		eventDTO.setEventName(event.getEventName());
		eventDTO.setEventHits(event.getEventHits());
		eventDTO.setEventStartDate(event.getEventStartDate());
		eventDTO.setEventEndDate(event.getEventEndDate());
		eventDTO.setEventOriginalImageName(event.getEventOriginalImageName());
		eventDTO.setEventServerImageName(event.getEventServerImageName());
		eventDTO.setEventOriginalThumbnailName(event.getEventOriginalThumbnailName());
		eventDTO.setEventServerThumbnailName(event.getEventServerThumbnailName());
			
		return eventDTO;
	}
	
	
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

}	