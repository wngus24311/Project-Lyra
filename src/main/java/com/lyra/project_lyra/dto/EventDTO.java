package com.lyra.project_lyra.dto;

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
@NoArgsConstructor
@AllArgsConstructor  
@Builder
public class EventDTO {
	// �̺�Ʈ ��ȣ
	private Long evnum;
	// �̺�Ʈ ����
	private String eventTitle;
	// �̺�Ʈ �̸�
	private String eventName;
	// ��ȸ��
	private int eventHits;
	// ���۳�¥
	private String eventStartDate;
	// ���ᳯ¥
	private String eventEndDate;
	
	private MultipartFile eventImage; // save.html -> Controller 
	private MultipartFile eventThumbnail; // save.html -> Controller

	private String eventOriginalImageName; 
	private String eventOriginalThumbnailName; 
	private String eventServerImageName; 
	private String eventServerThumbnailName; 
	
	
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