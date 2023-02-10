package com.lyra.project_lyra.entity.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lyra.project_lyra.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude ="event")
@Table(name="tbl_eventreview")
public class EventReview //extends BaseEntity
{
	//댓글 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewnum;

	/* 유저아이디 포링키 이후 사용 코드
	@ManyToOne(fetch = FetchType.LAZY)
	private MemberInfo memberinfo;
	*/
	// 포링키 이전까지 댓글유저 아이디 객체 대체용도 
	@Column(length=15, nullable=false)
	private String reviewid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Event event;
	
	// 댓글내용
	@Column(length=2000, nullable=false)
	private String reviewcontent;
	
	
	
}
