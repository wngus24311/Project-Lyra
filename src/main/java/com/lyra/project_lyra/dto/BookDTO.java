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
	 
	 //BookReview + ( bookNum )
	 private Long reviewNum;
	 private String id;
	 private String bookReview;
	 private Long grade;
	 private LocalDateTime bookReviewRegDate;
}
