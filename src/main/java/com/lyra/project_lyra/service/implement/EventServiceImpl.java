package com.lyra.project_lyra.service.implement;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.lyra.project_lyra.entity.event.EventReview;
import com.lyra.project_lyra.repository.event.EventRepository;
import com.lyra.project_lyra.repository.event.EventReviewRepository;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	
	private final EventRepository eventRepository;
	private final EventReviewRepository eventReviewRepository;
	
	public void register(EventDTO eventDTO) throws IOException {
			
			Long evnum = eventDTO.getEvnum();
			String eventTitle = eventDTO.getEventTitle();
			String eventName = eventDTO.getEventName();
			String eventStartDate = eventDTO.getEventStartDate();
			String eventEndDate = eventDTO.getEventEndDate();
			
			MultipartFile eventImage = eventDTO.getEventImage();
			MultipartFile eventThumbnail = eventDTO.getEventThumbnail();
			// 2. ?????? ??? ?????? //
			String eventOriginalImageName = eventImage.getOriginalFilename();
			String eventOriginalThumbnailName = eventThumbnail.getOriginalFilename();
			// 3. ???? ????? ????? ????
			String eventServerImageName = System.currentTimeMillis()+ "_" + eventOriginalImageName ;
			String eventServerThumbnailName = "s_" + System.currentTimeMillis() + "_" + eventOriginalThumbnailName;
			// 4. ???? ??? ????
			String saveImagePath = "C:/upload/" + eventServerImageName;
			String saveThumbnailPath = "C:/upload/" + eventServerThumbnailName;
			// 5. ??? ??ея? ???? ???? (?????&???? ????)
			eventImage.transferTo(new File(saveImagePath));
			eventThumbnail.transferTo(new File(saveThumbnailPath));

			Event eventFile = Event.toEventFileEntity(
					evnum, eventTitle, eventName, eventStartDate, eventEndDate, 
					eventOriginalImageName, eventServerImageName, 
					eventOriginalThumbnailName, eventServerThumbnailName);
			// DB?? ??????? ???.
			eventRepository.save(eventFile);
		
		
	}


	// ????? 
	@Transactional
	public void updateHits(Long evnum) {
		eventRepository.updateHits(evnum);
	}

	@Transactional
	public EventDTO findById(Long evnum) {
		// findByevnum : JpaRepository???? ????????? ????.
		Optional<Event> optionalEventEntity = eventRepository.findById(evnum);
		// Optional??u?? ????? if?????? true/false??? ???? ?????????.
		if (optionalEventEntity.isPresent()) {
			/* optionalBoardEntity?? ??????? BoardEntity?? ?????? BoardDTO?? ?????? ??? */
			Event event = optionalEventEntity.get();
			EventDTO eventDTO = toRegisterEntity(event);
			return eventDTO;
		} else {
			return null;
		}
		
	}
	
	// ??n? ????
	public EventDTO update(EventDTO boardDTO) {
		
		Event eventEntity = toUpdateEntity(boardDTO);
		
		eventRepository.save(eventEntity);
		// ??? ??n??? ????????? ?????????? ????
		return findById(boardDTO.getEvnum());
	}
	

	// ??n? ????
	public void delete(Long evnum) {
		eventRepository.deleteById(evnum);
	}

	// ?????? ???? ???????? ??
	//
	@Override
	public Page<EventDTO> paging(Pageable pageable) {
		// ?????????? ???????????.
		int page = pageable.getPageNumber() - 1;
		// ?? ???????? ?????? ?? ????
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
	
	@Override
	public List<EventDTO> reviewFindAll(Long evnum) {
		// select * from tbl_evnetreview where evnum=? order by id desc;
		Event event = eventRepository.findById(evnum).get();
		List<EventReview> eventReviewList = eventReviewRepository.findAllByEventOrderByEvrnumDesc(event);
		/* EntityList -> DTOList */
		List<EventDTO> eventDTOList = new ArrayList<>();
		for (EventReview eventReview : eventReviewList) {
			EventDTO eventDTO = toEventReviewDTO(eventReview, evnum);
			eventDTOList.add(eventDTO);
		}
		return eventDTOList;
	}
	
	@Override
	public Long reviewRegister(EventDTO eventDTO) {
		/* ?еш???(Event) ??? */
		Optional<Event> optionalEvent = eventRepository.findById(eventDTO.getEvnum());
		if (optionalEvent.isPresent()) {
			Event event = optionalEvent.get();
			EventReview eventReview = toRegisterReviewEntity(eventDTO, event);
			return eventReviewRepository.save(eventReview).getEvrnum();
		} else {
			return null;
		}
	}
	
	public void reviewDelete(Long evrnum) {
		eventReviewRepository.deleteById(evrnum);
	}
	
}
