package com.lyra.project_lyra.repository.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.book.BookInfo;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long>{

	static Page<Object[]> searchPage(String type, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}