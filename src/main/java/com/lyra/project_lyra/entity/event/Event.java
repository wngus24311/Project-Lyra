package com.lyra.project_lyra.entity.event;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_event")
public class Event {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evnum;
	// ũ��� 20, not null
	@Column
	private String eventTitle;
	@Column
	private String eventName;
	@Column
	private int eventHits;	
	@Column
	private String eventStartDate;
	@Column
	private String eventEndDate;
	@Column
	private String eventOriginalImageName;
	@Column
	private String eventServerImageName;
	@Column
	private String eventOriginalThumbnailName;
	@Column
	private String eventServerThumbnailName;
	
	
	
	public static Event toEventFileEntity(
		 Long evnum,
		 String eventTitle, String eventName, String eventStartDate, String eventEndDate,
		String eventOriginalImageName, String eventServerImageName, String eventOriginalThumbnailName, String eventServerThumbnailName) {
		
		Event eventFile = new Event();
		eventFile.setEvnum(evnum);
		eventFile.setEventTitle(eventTitle);
		eventFile.setEventName(eventName);
		eventFile.setEventStartDate(eventStartDate);
		eventFile.setEventEndDate(eventEndDate);
		eventFile.setEventOriginalImageName(eventOriginalImageName);
		eventFile.setEventServerImageName(eventServerImageName);
		eventFile.setEventOriginalThumbnailName(eventOriginalThumbnailName);
		eventFile.setEventServerThumbnailName(eventServerThumbnailName);
	return eventFile;
	}
}
	
