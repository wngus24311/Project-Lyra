package com.lyra.project_lyra.repository.book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.book.BookInfo;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long>{

	
	@Query("SELECT b FROM BookInfo b WHERE b.bookGerne = :category")
	List<BookInfo> findAllByCategoryQuery(String category);
	
	//책 좋아요 개수 변경
	@Modifying
    @Transactional
    @Query("UPDATE BookInfo b SET b.bookLike = :bookLike WHERE b.bookNum = :bookNum")
	void updateLikeCount(Long bookNum, Long bookLike);
}