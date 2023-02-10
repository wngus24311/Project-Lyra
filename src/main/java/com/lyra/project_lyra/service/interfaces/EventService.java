package com.lyra.project_lyra.service.interfaces;


import java.util.List;

import com.lyra.project_lyra.dto.EventDTO;
//import com.lyra.project_lyra.dto.PageRequestDTO;
//import com.lyra.project_lyra.dto.PageResultDTO;
import com.lyra.project_lyra.entity.event.Event;

public interface EventService {
	
	Long register(EventDTO eventDTO);
	List<EventDTO> getEventList(Long eventNum);
	EventDTO read(Long eventNum);
	void modify(EventDTO eventDTO);
	void remove(Long eventNum);
	

	
	default Event dtoToEntity(EventDTO eventDTO){
//		Map<String, Object> entityMap = new HashMap<>();
		
		Event event = Event.builder()
						   .eventnum(eventDTO.getEventNum())
						   .eventname(eventDTO.getEventName())
						   .eventtitle(eventDTO.getEventTitle())
						   .build();
		return event;
		
	}

	default EventDTO entityToDTO(Event event) {
		EventDTO eventDTO = EventDTO.builder()
									.eventNum(event.getEventnum())
									.eventTitle(event.getEventtitle())
//									.regDate(event.getRegDate())
//									.updateDate(event.getUpdateDate())
									.build();
		
		return eventDTO;
	}
}