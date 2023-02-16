package com.lyra.project_lyra.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.member.MemberInfo;


public interface MemberInfoRepository extends JpaRepository<MemberInfo, String>{

}