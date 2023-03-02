package com.lyra.project_lyra.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor //?? ??????
@AllArgsConstructor  // ??? ??? ????????? ??? ??????
@Builder
public class EventDTO {
	// ???? ???
	private Long evnum;
	// ???? ????
	private String eventTitle;
	// ???? ???
	private String eventName;
	// ?????
	private int eventHits;
	// ?????กอ
	private String eventStartDate;
	// ????กอ
	private String eventEndDate;
	// ??? ?????
	private String nickname;
	// ??? ??
	private int eventReviewCount;
	
	private MultipartFile eventImage; // save.html -> Controller 
	private MultipartFile eventThumbnail; // save.html -> Controller

	private String eventOriginalImageName; 
	private String eventOriginalThumbnailName; 
	private String eventServerImageName; 
	private String eventServerThumbnailName; 
	
	
	private Long evrnum;
	private String memberId;
	private String eventReviewContent;
	private LocalDateTime eventReviewRegdate;
	
	
	public EventDTO(Long evnum, String eventTitle, int eventHits, 
			String eventStartDate, String eventEndDate,
			String eventOriginalImageName, 
			String eventOriginalThumbnailName, 
			String eventServerImageName,
			String eventServerThumbnailName
			){
		this.evnum = evnum;
		this.eventTitle = eventTitle;
		this.eventHits = eventHits;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.eventOriginalImageName = eventOriginalImageName;
		this.eventOriginalThumbnailName = eventOriginalThumbnailName;
		this.eventServerImageName = eventServerImageName;
		this.eventServerThumbnailName = eventServerThumbnailName;

	}
	
	

}