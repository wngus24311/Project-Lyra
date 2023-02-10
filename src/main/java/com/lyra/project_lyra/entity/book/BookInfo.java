package com.lyra.project_lyra.entity.book;

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
   private Long bookNum;
   
   private String bookTitle;
   private String bookGerne;
   private Long bookLike;
   private int bookPage;
}