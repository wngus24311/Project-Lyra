package com.lyra.project_lyra.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor  // 모든 필드를 매개변수로 하는 생성자
public class EventDTO {
	// 이벤트 번호
	private Long evnum;
	// 이벤트 제목
	private String eventTitle;
	// 이벤트 이름
	private String eventName;
	// 조회수
	private int eventHits;
	// 시작날짜
	private LocalDateTime eventStartDate;
	// 종료날짜
	private LocalDateTime eventEndDate;
	
	// MultipartFile : 파일을 담을 수 있도록 스프링에서 제공해주는 인터페이스
	private MultipartFile eventImage; // save.html -> Controller 파일을 담는 용도
	private MultipartFile eventThumbnail; // save.html -> Controller 파일을 담는 용도

	// 파일이름을 구분짓기 위해 name을 두개로줌
	// 원본 파일 이름 
	private String eventOriginalImageName; 
	private String eventOriginalThumbnailName; 
	// 서버 저장용 파일 이름
	private String eventServerImageName; 
	private String eventServerThumbnailName; 
	
	// 이미지 첨부 여부(첨부 1, 미첨부 0) 파일을 첨부됐냐 안됐냐를 따지기위함.
	private int imageAttached;
	private int ThumbnailAttached;
	
	// 페이지에서 보여줄 목록들을 담은 생성자
	public EventDTO(Long evnum, String eventTitle, int eventHits, 
			LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
		this.evnum = evnum;
		this.eventTitle = eventTitle;
		this.eventHits = eventHits;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
	}
}

