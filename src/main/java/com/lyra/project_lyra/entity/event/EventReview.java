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
import com.lyra.project_lyra.entity.member.MemberInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude ="event")
@Table(name="tbl_eventreview")
@NoArgsConstructor
@AllArgsConstructor
public class EventReview extends BaseEntity{

	//´ñ±Û ¹øÈ£
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evrnum;

	@Column(length=2000)
	private String eventReviewContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evnum")
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private MemberInfo memberInfo;
	


}
