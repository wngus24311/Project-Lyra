package com.lyra.project_lyra.entity.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lyra.project_lyra.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name="tbl_event")
@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event //extends BaseEntity 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventnum;
	
	// 이벤트(주제)
	@Column(length=20, nullable=false)
	private String eventname;
	
	// 이벤트 게시글 제목
	@Column(length=20, nullable=false)
	private String eventtitle;
	
	
}
