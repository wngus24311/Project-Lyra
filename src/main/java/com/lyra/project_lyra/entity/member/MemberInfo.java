package com.lyra.project_lyra.entity.member;

import java.sql.Timestamp;

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
   private String id;
   
   private String pw;
   private int age;
   private int gender;
   private String nickname;
   private String memberGerne;
   private String subscribeState;
   private Timestamp lastlogin;

}