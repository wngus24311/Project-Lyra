package com.lyra.project_lyra.dto;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
	
	//이벤트 게시글 번호
	private Long eventNum;
	//이벤트 주제(이름) 예: 오독오독 
	private String eventName;
	//이벤트 게시글 제목 예 : 오늘의독서준비 오늘의독서완료 이벤트 안내.
	private String eventTitle;
	
	@Builder.Default
	private List<EventImageDTO>imageDTOList = new ArrayList<>();
/*	
	//평점
	private double avg;
	
	//리뷰 수
	private int reviewcount;
	
	private LocalDateTime regDate; 
	private LocalDateTime updateDate; 
 */
}
