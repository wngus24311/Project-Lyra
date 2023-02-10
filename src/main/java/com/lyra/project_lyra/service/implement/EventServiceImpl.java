package com.lyra.project_lyra.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.repository.event.EventRepository;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	
	@Override
	public Long register(EventDTO eventDTO) {
		Event event = dtoToEntity(eventDTO);
		eventRepository.save(event);
		return event.getEventnum();
	}
	@Override
	public List<EventDTO> getEventList(Long eventNum) {
		return eventRepository.findAll().stream().map(e ->
			entityToDTO(e)).collect(Collectors.toList());
	}

	@Override
	public EventDTO read(Long eventNum) {
		Optional<Event> event = eventRepository.findById(eventNum);
		return event.isPresent()?entityToDTO(event.get()):null;
	}
	@Override
	public void modify(EventDTO eventDTO) {
		Event event = dtoToEntity(eventDTO);
		eventRepository.save(event);
	}

	@Override
	public void remove(Long eventNum) {
		eventRepository.deleteById(eventNum);
	}

}
