package com.lyra.project_lyra.service.interfaces;

import com.lyra.project_lyra.dto.MemberDTO;

public interface MemberService {
    public String join(String username, String password, int age, int gender, String nickname, String memberGerne, String subscribeState);
    public String login(String userName, String password);
    public void addMemberGenre(String username, String memberGerne);
	public MemberDTO check(String username);
}
