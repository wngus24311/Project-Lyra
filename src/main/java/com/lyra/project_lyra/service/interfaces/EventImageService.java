package com.lyra.project_lyra.service.interfaces;

import java.util.List;

import com.lyra.project_lyra.dto.EventImageDTO;
import com.lyra.project_lyra.entity.event.EventImage;

public interface EventImageService {

	Long register(EventImageDTO eventImageDTO);
	List<EventImageDTO> getEventImageList(Long imageNum);
	EventImageDTO read(Long imageNum);
	void modify(EventImageDTO eventImageDTO);
	void remove(Long imageNum);
	
 //아이디-비밀번호 
	default EventImage dtoToEntity(EventImageDTO eventImageDTO){
			EventImage eventImage = EventImage.builder()
											  .path(eventImageDTO.getPath())
											  .imagename(eventImageDTO.getImageName())
											  .uuid(eventImageDTO.getUuid())
										//	  .event(event)
											  .build();
					return eventImage;
	}
	
	default EventImageDTO entityToDTO(EventImage eventImage) {
			
	EventImageDTO eventImageDTO = EventImageDTO.builder()
								.path(eventImage.getPath())
								.uuid(eventImage.getUuid())
								.imageName(eventImage.getImagename())
								.build();
		return eventImageDTO;
	}
}

