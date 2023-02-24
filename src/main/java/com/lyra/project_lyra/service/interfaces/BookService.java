package com.lyra.project_lyra.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.dto.PageRequestDTO;
import com.lyra.project_lyra.dto.PageResultDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.book.BookReview;
import com.lyra.project_lyra.entity.member.MemberInfo;

//1번 : BookInfo
//2번 : BookReview

public interface BookService {
	
	void insert(BookDTO dto);

	Long register1(BookDTO dto);
	
	Long register2(BookDTO dto);
	
	// 책 리뷰 페이지 처리
	List<BookDTO> getList(List<Long> likeBookNum, List<Long> keepBookNum);
	
	// 책 카테고리별 페이지 처리
	List<BookDTO> getCategoryList(String username, List<Long> likeBookNum, List<Long> keepBookNum);
	
	// 책 좋아요 개수 많은 순으로 가져오기
	List<BookDTO> getLikeList(List<Long> likeBookNum, List<Long> keepBookNum);
	
	// 책 최신 리스트 가져오기
	List<BookDTO> getUpdateList(List<Long> likeBookNum, List<Long> keepBookNum);
	
	List<BookDTO> getBookList(List<CombineDTO> combineDTO);

	// 책 번호로 List 가져오기
	
	
	// 랭킹 페이지 처리
	PageResultDTO<BookDTO, Object[]> getBookRankingList(PageRequestDTO pageRequestDto);

	void modify(BookDTO bookDTO);

	void remove(Long reviewNum);

	default BookInfo bookInfoDtoToEntity(BookDTO dto) {

		BookInfo bookInfo = BookInfo.builder()
				.bookNum(dto.getBookNum())
				.bookTitle(dto.getBookTitle())
				.bookThumbnail(dto.getBookThumbnail())
				.bookGerne(dto.getBookGerne())
				.bookLike(dto.getBookLike())
				.bookPage(dto.getBookPage())
				.build();

		return bookInfo;
	}

	default BookReview reviewDtoToEntity(BookDTO dto) {

		MemberInfo memberInfo = MemberInfo.builder().username(dto.getUsername()).build();

		BookInfo bookInfo = BookInfo.builder().bookNum(dto.getBookNum()).build();

		BookReview bookReview = BookReview.builder()
				.reviewnum(dto.getReviewNum())
				.memberInfo(memberInfo)
				.bookInfo(bookInfo).grade(dto.getGrade())
				.bookReview(dto.getBookReview())
				.bookReviewRegDate(dto.getBookReviewRegDate())
				.build();

		return bookReview;
	}

	default BookDTO bookInfoEntityToDto(BookInfo bookInfo) {

		BookDTO bookDTO = BookDTO.builder().bookNum(bookInfo.getBookNum())
				.bookTitle(bookInfo.getBookTitle())
				.bookThumbnail(bookInfo.getBookThumbnail())
				.bookGerne(bookInfo.getBookGerne())
				.bookLike(bookInfo.getBookLike())
				.bookPage(bookInfo.getBookPage())
				.build();

		return bookDTO;
	}

	default BookDTO reviewEntityToDto(BookReview bookReview) {

		BookDTO bookDTO = BookDTO.builder().reviewNum(bookReview.getReviewnum()).
				username(bookReview.getMemberInfo().getUsername())
				.bookNum(bookReview.getBookInfo().getBookNum())
				.bookReview(bookReview.getBookReview())
				.grade(bookReview.getGrade())
				.bookReviewRegDate(bookReview.getBookReviewRegDate())
				.build();

		return bookDTO;
	}
	
	default boolean entityNullCheck(BookInfo bookInfo) {
		boolean bResult = true;
		
		if (bookInfo.getBookGerne().equals("") || bookInfo.getBookThumbnail().equals("") || bookInfo.getBookTitle().equals("")) {
			bResult = false;
		}
		
		return bResult;
	}

}