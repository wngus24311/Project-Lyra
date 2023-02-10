package com.lyra.project_lyra.entity.combine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lyra.project_lyra.entity.book.BookInfo;
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
@ToString(exclude={"memberInfo", "bookInfo"})
@Table(name="tbl_CombineKeep")
public class CombineKeep {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long keepNum;
   
   @ManyToOne
   @JoinColumn(name="id")
   private MemberInfo memberInfo;
   
   @ManyToOne
   @JoinColumn(name="bookNum")
   private BookInfo bookInfo;
}