package com.lyra.project_lyra.service.implement;

import java.io.File;


import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.repository.event.EventRepository;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	
	private final EventRepository eventRepository;
	
	public void register(EventDTO eventDTO) throws IOException {
			
			Long evnum = eventDTO.getEvnum();
			String eventTitle = eventDTO.getEventTitle();
			String eventName = eventDTO.getEventName();
			String eventStartDate = eventDTO.getEventStartDate();
			String eventEndDate = eventDTO.getEventEndDate();
			
			MultipartFile eventImage = eventDTO.getEventImage();
			MultipartFile eventThumbnail = eventDTO.getEventThumbnail();
			String eventOriginalImageName = eventImage.getOriginalFilename();
			String eventOriginalThumbnailName = eventThumbnail.getOriginalFilename();
			String eventServerImageName = System.currentTimeMillis()+ "_" + eventOriginalImageName ;
			String eventServerThumbnailName = "s_" + System.currentTimeMillis() + "_" + eventOriginalThumbnailName;
			String saveImagePath = "C:/upload/" + eventServerImageName;
			String saveThumbnailPath = "C:/upload/" + eventServerThumbnailName;
			eventImage.transferTo(new File(saveImagePath));
			eventThumbnail.transferTo(new File(saveThumbnailPath));

			Event eventFile = Event.toEventFileEntity(
					evnum, eventTitle, eventName, eventStartDate, eventEndDate, 
					eventOriginalImageName, eventServerImageName, 
					eventOriginalThumbnailName, eventServerThumbnailName);
			eventRepository.save(eventFile);
		
		
	}


	@Transactional
	public void updateHits(Long evnum) {
		eventRepository.updateHits(evnum);
	}

	@Transactional
	public EventDTO findById(Long evnum) {
		Optional<Event> optionalEventEntity = eventRepository.findById(evnum);
		if (optionalEventEntity.isPresent()) {
			Event event = optionalEventEntity.get();
			EventDTO eventDTO = toRegisterEntity(event);
			return eventDTO;
		} else {
			return null;
		}
		
	}
	
	public EventDTO update(EventDTO boardDTO) {
		
		Event eventEntity = toUpdateEntity(boardDTO);
		
		eventRepository.save(eventEntity);
		return findById(boardDTO.getEvnum());
	}
	

	public void delete(Long evnum) {
		eventRepository.deleteById(evnum);
	}
	@Override
	public Page<EventDTO> paging(Pageable pageable) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 16;
		Page<Event> eventEntities = eventRepository
				.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "evnum")));


		Page<EventDTO> eventDTO = eventEntities.map(event -> new EventDTO(
				event.getEvnum(), event.getEventTitle(),
				event.getEventHits(), event.getEventStartDate(), event.getEventEndDate(), 
				event.getEventOriginalImageName(), event.getEventServerImageName(),
				event.getEventOriginalThumbnailName(), event.getEventServerThumbnailName()
				));
		

		return eventDTO;
	}

}

