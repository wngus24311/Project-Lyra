package com.lyra.project_lyra.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageDTO {
	
	// 경로
	private String path;
	// 이미지의 고유의 키 값
	private String uuid;
	// 이미지 파일 이름
	private String imageName;
	
	// 
	public String getImageURL() {
		try {
			return URLEncoder.encode(path + "/" + uuid+ "_" + imageName, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(path + "/s_" + uuid + "_" + imageName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
