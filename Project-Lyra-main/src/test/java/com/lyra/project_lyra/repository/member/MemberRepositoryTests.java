package com.lyra.project_lyra.repository.member;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyra.project_lyra.entity.member.MemberInfo;
import com.lyra.project_lyra.entity.member.MemberPurchase;


@SpringBootTest
public class MemberRepositoryTests {

   @Autowired
   private MemberInfoRepository memberInfoRepository;
   
   @Autowired
   private MemberPurchaseRepository memberPurchaseRepository;

   @Test
   public void beanTest() {
	   System.out.println(memberInfoRepository);
	   System.out.println(memberPurchaseRepository);
   }
   
   

    @Test
       public void insertMembers() {

       IntStream.rangeClosed(1,10).forEach(i -> {
               MemberInfo memberInfo = MemberInfo.builder()
                       .username("user" + i)
                       .password("1111")
                       .nickname("nickname"+i)
                       .age(i)
                       .memberGerne("액션")
                       .subscribeState("예")
                       .build();
               memberInfoRepository.save(memberInfo);

            memberInfoRepository.save(memberInfo);
        });
   
    }
    
    @Test
    public void insertMemberPurchase() {

    	 //맴버 아이디
        String username = "user"+((long)(Math.random()*10) + 1);
        MemberInfo memberInfo = MemberInfo.builder().username(username).build();

    IntStream.rangeClosed(1,10).forEach(i -> {
            MemberPurchase memberPurchase = MemberPurchase.builder()
                    .purchaseNum((long)i)
                    .memberInfo(memberInfo)
                    .purchaseDate(LocalDateTime.now())
                    .build();
            memberPurchaseRepository.save(memberPurchase);
     });

 }
    


}