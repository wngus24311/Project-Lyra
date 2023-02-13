package com.lyra.project_lyra.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.book.BookReview;

public interface BookReviewRepository extends JpaRepository<BookReview, Long>{
	   
	//리뷰 삭제
	   @Modifying
	   @Query("delete from BookReview br where br.bookInfo = :bookInfo")
	   void deleteByBookNum(BookInfo bookInfo);
	   
	 //책 리뷰 가져오기
	   List<BookReview> findByBookInfo(BookInfo bookInfo);
}


