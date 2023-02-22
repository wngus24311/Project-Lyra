package com.lyra.project_lyra.repository.member;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.dto.MemberDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.member.MemberInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface MemberInfoRepository extends JpaRepository<MemberInfo, String>{
    /** username으로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByUsername(String username);

    /** password로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByPassword(String password);

    /** username과 password로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByUsernameAndPassword(String username, String password);
    
    /** category 칼럼 데이터 수정 */
    @Modifying
    @Transactional
    @Query("UPDATE MemberInfo b SET b.memberGenre = :category WHERE b.username = :username")
	void updateCategory(String username, String category);
    
    //카테고리 가져오기
    @Query("SELECT b.memberGenre FROM MemberInfo b WHERE b.username = :username")
    String findCategory(String username);
    
    @Query("SELECT m FROM MemberInfo m WHERE m.username = :username")
    MemberInfo findUsernameInfo(String username);
    
  //책 좋아요 개수 변경
  	@Modifying
    @Transactional
    @Query("UPDATE MemberInfo m SET m.subscribeState = :membership WHERE m.username = :username")
  	void updateMembership(String membership, String username);
}