//package com.lyra.project_lyra.repository.book;
//
//import java.time.LocalDateTime;
//import java.util.stream.IntStream;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.lyra.project_lyra.entity.book.BookInfo;
//import com.lyra.project_lyra.entity.book.BookReview;
//import com.lyra.project_lyra.entity.member.MemberInfo;
//
//@SpringBootTest
//public class BookReviewRepositoryTests {
//
//	@Autowired
//	private BookReviewRepository bookReviewRepository;
//
//	@Test
//	public void beanTests() {
//		System.out.println(bookReviewRepository);
//	}
//
//    @Test
//    public void insertbookReview() {
//
// 	IntStream.rangeClosed(1,100).forEach(i -> {
//
//         //맴버 아이디
//         String username = "user"+((long)(Math.random()*10) + 1);
//         MemberInfo memberInfo = MemberInfo.builder().username(username).build();
//
//         //책 번호
//         Long bookNum  =  ((long)(Math.random()*10) + 1 );
//         BookInfo bookInfo = BookInfo.builder().bookNum(bookNum).build();
//         Long grade = ((long)(Math.random()*5) + 1 );
//
//         BookReview bookreview = BookReview.builder()
//        		 .reviewnum((long) i)
//        		 .memberInfo(memberInfo)
//        		 .bookInfo(bookInfo)
//        		 .bookReview("재미있어요"+i)
//        		 .grade(grade)
//        		 .bookReviewRegDate(LocalDateTime.now())
//        		 .build();
//
//        		 bookReviewRepository.save(bookreview);
//     });
//
// }
//}
