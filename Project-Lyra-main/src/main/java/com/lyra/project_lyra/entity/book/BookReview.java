package com.lyra.project_lyra.entity.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import com.lyra.project_lyra.entity.member.MemberInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude= {"memberInfo","bookInfo"})
@Table(name="tbl_BookReview")
public class BookReview {
	
    @Id
    private Long reviewnum;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")
    private MemberInfo memberInfo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookNum")
    private BookInfo bookInfo;
    
    @Column(length=2000)
    private String bookReview;
    
    private Long grade;
    
    @LastModifiedDate
	private LocalDateTime bookReviewRegDate;

	 public void changeGrade(Long grade) {
    	this.grade = grade;
    }
    
    public void changeReview(String bookReview) {
    	this.bookReview = bookReview;
    }
}
