package com.lyra.project_lyra.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	
     //BookInfo
	 private Long bookNum;	   
	 private String bookTitle;
	 private String bookThumbnail;
	 private String bookGerne;
	 private Long bookLike; 
	 private int bookPage;
	 
	 // 좋아요 찜하기 check
     private String likeCheck;
     private String keepCheck;
	 
	 //BookReview + ( bookNum )
	 private Long reviewnum;
	 private String username;
	 private String bookReview;
	 private Long grade;
	 private LocalDateTime bookReviewRegDate;
}
