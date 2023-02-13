package com.lyra.project_lyra.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.repository.event.EventRepository;

@SpringBootTest
public class EventPageTests {

	@Autowired
	private EventRepository boardRepository;
	
	@Test
	public void insertPageTests() {
		IntStream.range(0, 50).forEach(b -> {
			Event boardEntity = Event.builder()
												 .eventTitle(b + "번 테스트 제목입니다.")
												 .eventName(b + "번 테스트 중입니다.")
												 .build();
			boardRepository.save(boardEntity);
		});
	}
}
