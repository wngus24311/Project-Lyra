package com.lyra.project_lyra.entity.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@ToString
@Table(name="tbl_BookInfo")
public class BookInfo {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long bookNum;
   
   @Column(nullable = false)
   private String bookTitle;
   
   @Column(nullable = false)
   private String bookThumbnail;
   
   @Column(nullable = false)
   private String bookGerne;
   
   private Long bookLike;
   
   @Column(nullable = false)
   private int bookPage;
}