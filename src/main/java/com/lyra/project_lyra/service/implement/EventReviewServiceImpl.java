package com.lyra.project_lyra.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.EventReviewDTO;
import com.lyra.project_lyra.entity.event.EventReview;
import com.lyra.project_lyra.repository.event.EventReviewRepository;
import com.lyra.project_lyra.service.interfaces.EventReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventReviewServiceImpl implements EventReviewService {

	private final EventReviewRepository eventReviewRepository;

	// 생성
	@Override
	public Long register(EventReviewDTO eventReviewDTO) {
		EventReview eventReview = dtoToEntity(eventReviewDTO);
		eventReviewRepository.save(eventReview);
		return eventReview.getReviewnum();
	}
	// 읽기 : 댓글은 전체리스트만 보여줄거기에 read는 따로 안만들어줬다.
	@Override
	public List<EventReviewDTO> getEventReviewList(Long reviewNum) {
		return eventReviewRepository.findAll().stream().map(r->
			entityToDTO(r)).collect(Collectors.toList());
	}
	
	// 수정
	@Override
	public void modify(EventReviewDTO eventReviewDTO) {
		EventReview eventReview = dtoToEntity(eventReviewDTO);
		eventReviewRepository.save(eventReview);
	}
	
	// 삭제 
	@Override
	public void remove(Long reviewnum) {
		eventReviewRepository.deleteById(reviewnum);
	}

}
