package com.lyra.project_lyra.service.interfaces;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.dto.PageRequestDTO;
import com.lyra.project_lyra.dto.PageResultDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.book.BookReview;
import com.lyra.project_lyra.entity.member.MemberInfo;

//1번 : BookInfo
//2번 : BookReview

public interface BookService {
		
	  Long register(BookDTO dto);
	  
	  //랭킹 페이지 처리
	  PageResultDTO<BookDTO, Object[]> getBookRankingList(PageRequestDTO pageRequestDto);
	  
	  //책 장르별 페이지 처리	 
	  PageResultDTO<BookDTO, Object[]> getBookGerneList(PageRequestDTO pageRequestDto);
	  
	  //책 리뷰 페이지 처리
	  PageResultDTO<BookDTO, Object[]> getBookReviewList(PageRequestDTO pageRequestDto);
	  
	  BookDTO get(Long bno);
	  
	  void modify(BookDTO bookDTO);
	 
	default BookInfo dtoToEntity1(BookDTO dto) {

		BookInfo bookInfo = BookInfo.builder()
				.bookNum(dto.getBookNum())
				.bookTitle(dto.getBookTitle())
				.bookGerne(dto.getBookGerne())
				.bookLike(dto.getBookLike())
				.bookPage(dto.getBookPage())
				.build();
		
		return bookInfo;
	}
	
	default BookReview dtoToEntity2(BookDTO dto) {
		
		MemberInfo memberInfo = MemberInfo.builder().id(dto.getId()).build();
		
		BookInfo bookInfo = BookInfo.builder().bookNum(dto.getBookNum()).build();

		BookReview bookReview = BookReview.builder()
				.reviewnum(dto.getReviewNum())
				.memberInfo(memberInfo)
				.bookInfo(bookInfo)
				.grade(dto.getGrade())
				.bookReviewRegDate(dto.getBookReviewRegDate())				
				.build();
		
		return bookReview;
	}
	
	default BookDTO entityToDto1(BookInfo bookInfo) {
				
		BookDTO bookDTO = BookDTO.builder()
				.bookNum(bookInfo.getBookNum())
				.bookTitle(bookInfo.getBookTitle())
				.bookGerne(bookInfo.getBookGerne())
				.bookLike(bookInfo.getBookLike())
				.bookPage(bookInfo.getBookPage())
				.build();
	
		return bookDTO;
	}
	
	default BookDTO entityToDto2(BookReview bookReview, MemberInfo memberInfo, BookInfo bookInfo) {
		
		BookDTO bookDTO = BookDTO.builder()
				.reviewNum(bookReview.getReviewnum())
				.id(memberInfo.getId())
				.bookNum(bookInfo.getBookNum())
				.bookReview(bookReview.getBookReview())
				.grade(bookReview.getGrade())
				.bookReviewRegDate(bookReview.getBookReviewRegDate())				
				.build();
	
		return bookDTO;
	}

}