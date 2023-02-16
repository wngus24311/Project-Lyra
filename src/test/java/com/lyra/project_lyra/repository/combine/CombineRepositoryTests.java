package com.lyra.project_lyra.repository.combine;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.combine.CombineCompleteRate;
import com.lyra.project_lyra.entity.combine.CombineKeep;
import com.lyra.project_lyra.entity.member.MemberInfo;



@SpringBootTest
public class CombineRepositoryTests {

   @Autowired
   private CombineCompleteRateRepository combineCompleteRateRepository;
   	
   @Autowired private CombineKeepRepository combineKeepRepository;
	 

   @Test
   public void beanTest() {
	   System.out.println(combineCompleteRateRepository);
	   System.out.println(combineKeepRepository); 
   }
   
   

       @Test
       public void insertCombineCompleteRate() {

    	IntStream.rangeClosed(1,30).forEach(i -> {

            //맴버 아이디
            String id = "user"+((long)(Math.random()*10) + 1);
            MemberInfo memberInfo = MemberInfo.builder().id(id).build();

            //책 번호
            Long bookNum  =  ((long)(Math.random()*10) + 1 );
            BookInfo bookInfo = BookInfo.builder().bookNum(bookNum).build();

            CombineCompleteRate combineCompleteRate = CombineCompleteRate.builder()
            		.completeRateNum((long) i)
            		.memberInfo(memberInfo)
                    .bookInfo(bookInfo)
                    .lastPage((long) i)
                    .build();

            combineCompleteRateRepository.save(combineCompleteRate);
        });
   
    }
    

       @Test
       public void insertCombineKeep() {

    	IntStream.rangeClosed(1,30).forEach(i -> {

            //맴버 아이디
            String id = "user"+((long)(Math.random()*10) + 1);
            MemberInfo memberInfo = MemberInfo.builder().id(id).build();

            //책 번호
            Long bookNum  =  ((long)(Math.random()*10) + 1 );
            BookInfo bookInfo = BookInfo.builder().bookNum(bookNum).build();

            CombineKeep combineKeep = CombineKeep.builder()
            		.keepNum((long) i)
            		.memberInfo(memberInfo)
                    .bookInfo(bookInfo)
                    .build();

            combineKeepRepository.save(combineKeep);
        });
   
    }
}
