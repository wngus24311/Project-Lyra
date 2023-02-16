package com.lyra.project_lyra.entity.member;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

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
@Table(name="tbl_MemberPurchase")
public class MemberPurchase {
   
   @Id
   private Long purchaseNum;
   
   @ManyToOne
   @JoinColumn(name="id")
   private MemberInfo memberInfo;
   
   @LastModifiedDate
   private LocalDateTime purchaseDate;
}