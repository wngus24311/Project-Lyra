package com.lyra.project_lyra.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.member.MemberInfo;

import java.util.Optional;


public interface MemberInfoRepository extends JpaRepository<MemberInfo, String>{

    /** username으로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByUsername(String username);

    /** password로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByPassword(String password);

    /** username과 password로 DB 불러오는 메서드 */
    Optional<MemberInfo> findByUsernameAndPassword(String username, String password);
}