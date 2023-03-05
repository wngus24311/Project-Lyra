package com.lyra.project_lyra.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

   private String username;
   private String password;
   private int age;
   private int gender;
   private String nickname;
   private String memberGerne;
   private String subscribeState;
   private Timestamp lastlogin;
   
   private String checkUsername;

   private Long purchaseNum;
   private LocalDateTime purchaseDate;
   
}