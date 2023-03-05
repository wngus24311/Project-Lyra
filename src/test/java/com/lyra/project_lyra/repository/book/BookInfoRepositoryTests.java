package com.lyra.project_lyra.repository.book;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyra.project_lyra.entity.book.BookInfo;



@SpringBootTest
public class BookInfoRepositoryTests {

   @Autowired
   private BookInfoRepository bookInfoRepository;

   @Test
   public void beanTest() {
	   System.out.println(bookInfoRepository);
   }
   
   

    @Test
       public void insertMembers() {

       IntStream.rangeClosed(1,10).forEach(i -> {
               BookInfo bookInfo = BookInfo.builder()
                       .bookNum((long) i)
                       .bookTitle("book"+i)
                       .bookGerne("액션")
                       .bookLike((long) i)
                       .bookPage(i)
                       .build();
               bookInfoRepository.save(bookInfo);
        });
   
    }
    


}