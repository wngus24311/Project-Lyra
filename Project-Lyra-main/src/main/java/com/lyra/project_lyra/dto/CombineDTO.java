package com.lyra.project_lyra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombineDTO {
       
	   private Long completeRateNum;
	   private Long keepNum;
	   private String username;
	   private Long bookNum;
	   private Long lastPage;
	   
}
