package com.lyra.project_lyra.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// FileUpload설정 클래스
@Configuration // 설정 클래스임을 지정.
public class WebConfig implements WebMvcConfigurer{
	private String resourcePath = "/upload/**"; // view에서 표현할 경로 및 view에서 접근할 경로
	private String savePath = "file:///upload/"; // 실제 서버가 이미지를 불러올때 접근할 파일 저장 경로
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcePath)
				.addResourceLocations(savePath);
	}
	
}
