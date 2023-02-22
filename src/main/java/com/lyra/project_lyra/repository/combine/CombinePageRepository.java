package com.lyra.project_lyra.repository.combine;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.combine.CombinePage;

public interface CombinePageRepository extends JpaRepository<CombinePage, Long>{
	//책 좋아요 개수 변경
	@Modifying
    @Transactional
    @Query("UPDATE CombinePage b SET b.bookPage = :bookPage WHERE b.pageNum = :bookNum")
	void updateBookPage(Long bookNum, Long bookPage);
}