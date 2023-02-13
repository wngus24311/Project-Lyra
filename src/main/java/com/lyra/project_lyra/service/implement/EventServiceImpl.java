package com.lyra.project_lyra.service.implement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.entity.event.EventImage;
import com.lyra.project_lyra.repository.event.EventImageRepository;
import com.lyra.project_lyra.repository.event.EventRepository;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EventImageRepository eventFileRepository;

	// Entity 클래스 타입으로 받도록 되어있다.
	public void save(EventDTO eventDTO) throws IOException {
		// 파일 첨부 여부에 따라 로직 분리
		if (eventDTO.getEventImage().isEmpty()) {
			// 첨부 파일이 없을 경우 기존방식대로 saveEntity가 되도록.
			Event event = toSaveEntity(eventDTO);
			eventRepository.save(event);

		} else {
			/*
			 * board_table에 해당 데이터 save 처리 board_file_table에 해당 데이터 save 처리
			 */
			// 1. DTO에 담긴 파일을 꺼냄
			MultipartFile eventImage = eventDTO.getEventImage();
			MultipartFile eventThumbnail = eventDTO.getEventThumbnail();
			// 2. 파일의 이름 가져옴
			String eventOriginalImageName = eventImage.getOriginalFilename();
			String eventOriginalThumbnailName = eventThumbnail.getOriginalFilename();
			// 3. 서버 저장용 이름을 만듬
			String eventServerImageName = System.currentTimeMillis() + "_" + eventOriginalImageName;
			String eventServerThumbnailName = System.currentTimeMillis() - 1 + "_" + eventOriginalThumbnailName;
			// 4. 저장 경로 설정
			String saveImagePath = "C:/springboot_img/" + eventServerImageName;
			String saveThumbnailPath = "C:/springboot_img/" + eventServerThumbnailName;
			// 5. 해당 경로에 파일 저장 (이미지&썸일 저장)
			eventImage.transferTo(new File(saveImagePath));
			eventThumbnail.transferTo(new File(saveThumbnailPath));

			Event eventEntity = toSaveFileEntity(eventDTO);
			// getevnum를 하는 이유는 부모자식 관계를 나타내기에 선언.
			Long saveEvnum = eventRepository.save(eventEntity).getEvnum();
			// 부모Entity자체가 자식Entity에게 전달되어야하기에 부모Entity를 다시 가져오는 작업.
			Event event = eventRepository.findById(saveEvnum).get();
			// BoardFileEntity객체로 변환하는 과정
			EventImage eventFileEntity = EventImage.toBoardFileEntity(event, eventOriginalImageName,
					eventServerImageName, eventOriginalThumbnailName, eventServerThumbnailName);
			// DB에 저장까지 완료.
			eventFileRepository.save(eventFileEntity);
		}
	}

	@Transactional
	// Entity의 객체를 List메소드에 담는 작업
	public List<EventDTO> findAll() {
		// Repository에서 객체를 가져올때는 Entity로 온다.
		// Entity로 넘어온 객체들을 BoardDTO객체로 옮겨담아서 다시 컨트롤로 return을 해줘야함.
		List<Event> boardEntityList = eventRepository.findAll();
		// 리스트 객체를 먼저 선언.
		List<EventDTO> boardDTOList = new ArrayList<>();
		// 반복문을 돌려서 DTOList에 하나씩 담는작업
		for (Event boardEntity : boardEntityList) {
			// Entity객체를 DTO객체로 변환하고 변환된 객체를 List리스트에 담는과정
			boardDTOList.add(toBoardDTO(boardEntity));
		}

		return boardDTOList;
		// entity객체를 DTO로 옳겨 담는 메소드 선언은 DTO에다가 선언함.
	}

	@Transactional
	public void updateHits(Long evnum) {
		eventRepository.updateHits(evnum);

	}

	@Transactional
	public EventDTO findById(Long evnum) {
		// findByevnum : JpaRepository에서 제공해주는 메소드.
		Optional<Event> optionalBoardEntity = eventRepository.findById(evnum);
		// Optional객체로 넘어왔기에 if문으로 true/false일때 값을 넣어줘야한다.
		if (optionalBoardEntity.isPresent()) {
			/* optionalBoardEntity를 가져와서 BoardEntity에 넣어준뒤 BoardDTO로 변환하는 작업 */
			Event event = optionalBoardEntity.get();
			EventDTO eventDTO = toBoardDTO(event);
			return eventDTO;
		} else {
			return null;
		}
	}

	// 게시글 수정
	public EventDTO update(EventDTO boardDTO) {
		Event boardEntity = toUpdateEntity(boardDTO);
		eventRepository.save(boardEntity);
		// 해당 게시글의 상세조회값을 넘겨주기위해 리턴
		return findById(boardDTO.getEvnum());
	}

	// 게시글 삭제
	public void delete(Long evnum) {
		eventRepository.deleteById(evnum);
	}

	// 페이지 값을 가져오는 곳
	//
	public Page<EventDTO> paging(Pageable pageable) {
		// 몇페이지를 보여주게할지.
		int page = pageable.getPageNumber() - 1;
		// 한 페이지에 보여줄 글 갯수
		int pageLimit = 3;
		Page<Event> eventEntities = eventRepository
				.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "evnum")));
		System.out.println("요청 페이지에 해당하는 글 => " + eventEntities.getContent());
		System.out.println("전체 글갯수 => " + eventEntities.getTotalElements());
		System.out.println("DB로 요청한 페이지 번호 => " + eventEntities.getNumber());
		System.out.println("전체 페이지 갯수 => " + eventEntities.getTotalPages());
		System.out.println("한 페이지에 보여지는 글 갯수 => " + eventEntities.getSize());
		System.out.println("이전 페이지 존재 여부 => " + eventEntities.hasPrevious());
		System.out.println("첫 페이지 여부 => " + eventEntities.isFirst());
		System.out.println("마지막 페이지 여부 => " + eventEntities.isLast());
		/*
		 * Page객체를 DTO객체로 변환해서 가져가는 방법 - map() Method : forEach처럼 Entity객체를 하나씩 꺼내서 DTO로
		 * 객체로 옮겨담는 작업. 목록 : evnum, title, hits, createdTime 정보만 담을 수 있는 DTO생성자
		 * 추가(BoardDTO에 선언)
		 */
		Page<EventDTO> eventDTO = eventEntities.map(event -> new EventDTO(event.getEvnum(), event.getEventTitle(),
				event.getEventHits(), event.getCreatedTime(), event.getUpdatedTime()));
		return eventDTO;
	}


}
