package com.lyra.project_lyra.entity.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="tbl_MemberInfo")
public class MemberInfo {
   
   @Id
   @Column(nullable = false)
   private String id;
   
   @Column(nullable = false)
   private String pw;
   
   @Column(nullable = false)
   private int age;
   
   @Column(nullable = false)
   private int gender;
   
   @Column(nullable = false)
   private String nickname;
   
   @Column(nullable = false)
   private String memberGerne;
   
   private String subscribeState;
   
   @Column(nullable = false)
   private Timestamp lastlogin;
}