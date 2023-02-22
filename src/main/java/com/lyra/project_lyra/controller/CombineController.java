package com.lyra.project_lyra.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyra.project_lyra.service.interfaces.CombineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/combine")
@Log4j2
@RequiredArgsConstructor
public class CombineController {
	
	private final CombineService combineService;
	
	@PostMapping("/keep")
	public void setKeepInsert(Model model, @RequestBody Map<String,Object> data, Authentication authentication) {
		String bookNum = (String)data.get("bookNums");
		String url = (String)data.get("url");
		Long lBookNum = Long.parseLong(bookNum);
		
		String username = (String)authentication.getPrincipal();
		log.info(username);
		
		//String[] usernames = url.split("http://localhost:8095/main/main/");
		//String username = usernames[1];
		
		if (combineService.bookKeepDelete(username, lBookNum)) {
			log.info("keep delete success");
		} else {
			combineService.bookKeepSave(username, lBookNum);			
			log.info("keep Insert success");
		}
	}
	
	@PostMapping("/like")
	public void setLikeInsert(Model model, @RequestBody Map<String,Object> data, Authentication authentication) {
		String bookNum = (String)data.get("bookNums");
		String url = (String)data.get("url");
		Long lBookNum = Long.parseLong(bookNum);
		
		//String[] usernames = url.split("http://localhost:8095/main/main/");
		//String username = usernames[1];
		String username = (String)authentication.getPrincipal();
		
		if (combineService.bookLikeDelete(username, lBookNum)) {
			log.info("keep delete success");
		} else {
			combineService.bookLikeSave(username, lBookNum);		
			log.info("keep Insert success");
		}
	}
	
	@PostMapping("/page")
	public void setPageInsert(Model model, @RequestBody Map<String,Object> data, Authentication authentication) {
		String bookNum = (String)data.get("bookNums");
		String url = (String)data.get("url");
		String bookPage = (String)data.get("bookPage");
		Long lBookNum = Long.parseLong(bookNum);
		Long lBookPage = Long.parseLong(bookPage);
		
		//String[] usernames = url.split("http://localhost:8095/main/main/");
		//String username = usernames[1];
		String username = (String)authentication.getPrincipal();
		
		if (combineService.bookPageUpdate(username, lBookNum, lBookPage)) {
			log.info("keep update success");
		} else {
			combineService.bookPageSave(username, lBookNum, lBookPage);
			log.info("keep Insert success");
		}
	}
}
