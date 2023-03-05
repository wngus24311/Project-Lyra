package com.lyra.project_lyra.entity.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Table(name="tbl_MemberInfo")
public class MemberInfo {
   
   @Id
   @Column(length=20, nullable = false)
   private String username;
   
   @Column(length=100, nullable = false)
   private String password;
   
   @Column(nullable = false)
   private int age;
   
   @Column(nullable = false)
   private int gender;
   
   @Column(nullable = false)
   private String nickname;

   private String memberGenre;
   
   private String subscribeState;
   
   @Column(nullable = false)
   private Timestamp lastlogin;
}