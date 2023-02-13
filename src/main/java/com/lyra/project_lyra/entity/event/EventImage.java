package com.lyra.project_lyra.entity.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lyra.project_lyra.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tbl_image")
public class EventImage extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	@Column
	private String eventOriginalImageName;
	@Column
	private String eventServerImageName;
	
	@Column
	private String eventOriginalThumbnailName;
	@Column
	private String eventServerThumbnailName;

	
	
	/* @JoinColumn(name = "board_id")
	 	- Board_id와 조인컬럼을 하겠다는 의미.
	 	- name = DB에 만들어지는 컬럼  */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evnum")
	private Event event;
	
	
	// 부모Entity, Fiiename 을 가져오도록. Pk값이 아니라 부모 Entity값을 넘겨줘야함.
	public static EventImage toBoardFileEntity(
			Event event, String eventOriginalImageName, String eventServerImageName, String eventOriginalThumbnailName, String eventServerThumbnailName) {
		EventImage boardFileEntity = new EventImage();
		boardFileEntity.setEvent(event);
		boardFileEntity.setEventOriginalImageName(eventOriginalImageName);
		boardFileEntity.setEventServerImageName(eventServerImageName);
		boardFileEntity.setEventOriginalThumbnailName(eventOriginalThumbnailName);
		boardFileEntity.setEventServerThumbnailName(eventServerThumbnailName);
	return boardFileEntity;
	}
	
}
