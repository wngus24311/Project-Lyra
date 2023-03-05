package com.lyra.project_lyra.repository.combine;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.combine.CombinePage;

public interface CombinePageRepository extends JpaRepository<CombinePage, Long>{
	/** bookPage 칼럼 데이터 수정 */
    @Modifying
    @Transactional
    @Query("UPDATE CombinePage c SET c.bookPage = :bookPage WHERE c.memberInfo.username = :username AND c.bookInfo.bookNum = :bookNum")
	void pageUpdate(Long bookPage, String username, Long bookNum);
}