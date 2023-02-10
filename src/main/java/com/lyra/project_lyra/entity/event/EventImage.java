package com.lyra.project_lyra.entity.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
// cxclude를 시키면 ToString메소드에서 제외시킨다.
@ToString(exclude = "event")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_eventimage")
public class EventImage {
	// 이미지 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imagenum;
	
	@Column(nullable= false)
	private String path;
	// uuid : 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
	@Column(nullable= false)
	private String uuid;
	@Column(length=100, nullable=false)
	private String imagename;

	@ManyToOne(fetch = FetchType.LAZY)
	private Event event;
}
