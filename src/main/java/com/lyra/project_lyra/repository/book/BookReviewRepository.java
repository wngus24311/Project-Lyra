package com.lyra.project_lyra.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.book.BookReview;
import com.lyra.project_lyra.entity.member.MemberInfo;

public interface BookReviewRepository extends JpaRepository<BookReview, Long>{
	   
	   @Modifying
	   @Query("delete from tbl_BookReview br where br.memberInfo = :memberInfo")
	   void deleteByMemberInfo(MemberInfo memberInfo);
	   
	 //책 리뷰 가져오기
		List<BookReview> getReviews(BookInfo bookInfo);
}