package com.lyra.project_lyra.repository.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.book.BookInfo;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long>{

	//검색
	static Page<Object[]> searchPage(String type, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//책 랭킹
	@Query("select m, avg(coalesce(r.grade,0)), count(r) from BookInfo m " 
			+ "left outer join BookReview r on r.bookInfo = m group by m")
	Page<Object[]> getBookRankingPage(Pageable pageable);
	
	@Query("SELECT b FROM BookInfo b WHERE b.bookGerne = :category")
	List<BookInfo> findAllByCategoryQuery(String category);
}