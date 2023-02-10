package com.lyra.project_lyra.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.EventImageDTO;
import com.lyra.project_lyra.entity.event.EventImage;
import com.lyra.project_lyra.repository.event.EventImageRepository;
import com.lyra.project_lyra.service.interfaces.EventImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
public class EventImageServiceImpl implements EventImageService {

	
	private final EventImageRepository eventImageRepository;
	
	@Override
	public Long register(EventImageDTO eventImageDTO) {
		EventImage eventImage = dtoToEntity(eventImageDTO);
		eventImageRepository.save(eventImage);		
		return eventImage.getImagenum();
	}

	@Override
	public List<EventImageDTO> getEventImageList(Long eventNum) {
		return eventImageRepository.findAll().stream().map(i->
			entityToDTO(i)).collect(Collectors.toList());
	}
	
	@Override
	public EventImageDTO read(Long imageNum) {
		Optional<EventImage> image = eventImageRepository.findById(imageNum);
		return image.isPresent()?entityToDTO(image.get()):null;
	}
	
	@Override
	public void modify(EventImageDTO eventImageDTO) {
		EventImage eventImage = dtoToEntity(eventImageDTO);
		eventImageRepository.save(eventImage);
	}

	@Override
	public void remove(Long imageNum) {
		eventImageRepository.deleteById(imageNum);
	}




}
