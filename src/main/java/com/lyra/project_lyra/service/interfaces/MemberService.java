package com.lyra.project_lyra.service.interfaces;

public interface MemberService {
    public String join(String username, String password, int age, int gender, String nickname, String memberGerne, String subscribeState);
    public String login(String userName, String password);
	
}
