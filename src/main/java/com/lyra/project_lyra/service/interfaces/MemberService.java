package com.lyra.project_lyra.service.interfaces;

import java.util.List;

import com.lyra.project_lyra.dto.MemberDTO;

public interface MemberService {
    public String join(String username, String password, int age, int gender, String nickname, String memberGerne, String subscribeState);
    public String login(String userName, String password);
    
    // 회원 정보 가져오기
 	List<MemberDTO> getList();
 	
 	// user id별 카테고리 가져오기
 	String[] getCategory(String username);
 	
 	//username 받아와서 Database 변경하기
 	void categoryInsert(String username, String category);
 	
 	MemberDTO getUsernameInfo(String username);
 	
 	void updateMembership(String membership, String username);
 	
 	String getMembership(String username);
}
