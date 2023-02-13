package com.lyra.project_lyra.entity.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lyra.project_lyra.entity.BaseEntity;

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
public class Event extends BaseEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evnum;
	// 크기는 20, not null
	@Column
	private String eventTitle;
	
	@Column
	private String eventName;
	@Column
	private int eventHits;	
	
	// 파일이 첨부 됐는지 안됐는지 여부를 따지기위해 생성
	@Column
	private int imageAttached;
	@Column
	private int ThumbnailAttached;
	
	// mappedBy : 어떤 것과 매칭을 할건지(boardFileEntity의 boardEntity 컬럼과 매칭)
	// 여러개가 올 수 있도록 board하나에 boardFile이 리스트형태로 가져와야하기에 리스트 타입으로 선언.
	@OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, 
				orphanRemoval = true, fetch = FetchType.LAZY)
	private List<EventImage> eventImageEntityList = new ArrayList<>();
	
}
	
