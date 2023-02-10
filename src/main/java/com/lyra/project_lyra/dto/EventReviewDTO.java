package com.lyra.project_lyra.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventReviewDTO {

	// 이벤트게시글 번호
	private Long eventNum;
	
	// 댓글 번호
	private Long reviewNum;
	// 댓글 아이디
	private String reviewId;
	// 댓글 내용
	private String reviewContent;
/*
	// 등록 날짜
	private LocalDateTime regDate;
	// 수정 날짜
	private LocalDateTime updateDate;
*/
}
