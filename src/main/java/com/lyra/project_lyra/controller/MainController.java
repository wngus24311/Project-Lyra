package com.lyra.project_lyra.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.repository.book.BookInfoRepository;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.interfaces.BookService;
import com.lyra.project_lyra.service.interfaces.CombineService;
import com.lyra.project_lyra.service.interfaces.MemberService;
import com.lyra.project_lyra.util.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor
public class MainController {
	private final BookService bookService;
	private final MemberService memberService;
	private final MemberInfoRepository repository;
	private final CombineService combineService;

	@GetMapping("/main")
	public ModelAndView getMainList(Model model, @RequestParam(value = "name", required=false) String loginUser) throws Exception{
		String username;
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}	
		
		String[] categoryOne = memberService.getCategory(username);
		
		model.addAttribute("username", username);
		model.addAttribute("bookResult", categoryDBtoView(username));
		model.addAttribute("result1", bookService.getCategoryList(categoryOne[0], combineService.bookLikeList(username), combineService.bookKeepList(username)));
		model.addAttribute("result2", bookService.getCategoryList(categoryOne[1], combineService.bookLikeList(username), combineService.bookKeepList(username)));
		model.addAttribute("bookLikeList", bookService.getLikeList(combineService.bookLikeList(username), combineService.bookKeepList(username)));
		model.addAttribute("bookUpdateList", bookService.getUpdateList(combineService.bookLikeList(username), combineService.bookKeepList(username)));
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/main");
        return modelAndView;
	}
	
	@PostMapping("/main")
	public String getMainList(Authentication authentication) throws Exception{		
		String username = (String)authentication.getPrincipal();
		
		return username;
	}
	
	@PostMapping("/categoryInsert")
	public void setCategoryInsert(Model model, @RequestBody Map<String,Object> data, Authentication authentication) {
		String category = (String)data.get("category");
		String url = (String)data.get("url");
		
		log.info("category : " + category);
		
		String categoryEntity = categoryViewtoDB(category);
		String username = (String)authentication.getPrincipal();
		
		log.info("categoryEntity : " + categoryEntity);
		log.info(username);
		
		memberService.categoryInsert(username, categoryEntity);
		log.info("category Insert Success");
	}
	
	 @GetMapping("/genrepage")
    public ModelAndView gernepage(Model model, @RequestParam(value = "name", required=false) String loginUser) {
    	String username;
		log.info(loginUser);
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}
		
		model.addAttribute("list", bookService.getList(combineService.bookLikeList(username), combineService.bookKeepList(username)));
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/genrepage");
        return modelAndView;
    }
    
    @PostMapping("/genrepage")
	public String getCategory(Authentication authentication) throws Exception{
		String username = (String)authentication.getPrincipal();
		
		return username;
	}
    
    @GetMapping("/bookflip")
    public ModelAndView getBookflip(Model model,@RequestParam(value = "name", required=false) String loginUser,@RequestParam(value = "num", required=false) Long bookNum) {
    	String username;
    	Long bookNums;
    	
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}	
		
		if (bookNum == null) {
			bookNums = 1L;
		}else {
			bookNums = bookNum;			
		}	
		
		model.addAttribute("username", username);
		model.addAttribute("bookNum", bookNums);
		
        log.info("/main/bookflip");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/bookflip");
        return modelAndView;
    }
    
    @PostMapping("/bookflip")
	public String getBookflip(Authentication authentication, @RequestBody Map<String,Object> data) throws Exception{
		String username = (String)authentication.getPrincipal();

		return username;
	}
    
    @PostMapping("/pageInsert")
	public void setPageInsert(@RequestBody Map<String,Object> data, Authentication authentication) {
		String bookNum = (String)data.get("bookNums");
		String bookPage = (String)data.get("bookPage");
		String username = (String)authentication.getPrincipal();
		
		Long lbookNum = Long.parseLong(bookNum); 
		Long lbookPage = Long.parseLong(bookPage); 
		
		log.info("bookNum : " + bookNum);
		log.info("bookPage : " + bookPage);
		log.info("username : " + username);

		combineService.bookPageSave(username, lbookNum, lbookPage);
	}
    
    @PostMapping("/modal")
	public Long modalShow(@RequestBody Map<String,Object> data, Authentication authentication) {
		String bookNum = (String)data.get("bookNums");
		
		Long lbookNum = Long.parseLong(bookNum); 
		
		log.info("bookNum : " + bookNum);
		
		return lbookNum;
	}

	// Entity -> DTO
	public String[] categoryDBtoView(String username) {
		String[] categoryName = memberService.getCategory(username);

		for (int i = 0; i < categoryName.length; i++) {
			switch (categoryName[i]) {
				case "1":
					categoryName[i] = "#액션";
					break;
				case "2":
					categoryName[i] = "#로맨스";
					break;
				case "3":
					categoryName[i] = "#추리";
					break;
				case "4":
					categoryName[i] = "#에세이";
					break;
				case "5":
					categoryName[i] = "#교육";
					break;
				case "6":
					categoryName[i] = "#소설";
					break;
				case "7":
					categoryName[i] = "#기술";
					break;
				case "8":
					categoryName[i] = "#인문";
					break;
				case "9":
					categoryName[i] = "#경영";
					break;
				case "10":
					categoryName[i] = "#여행";
					break;
				case "11":
					categoryName[i] = "#요리";
					break;
				case "12":
					categoryName[i] = "#건강";
					break;
				default:
					categoryName[i] = "#실시간 검색 순위";
					break;
			}
		}
		
		return categoryName;
	}
	
	public String categoryViewtoDB(String category) {
		String categoryName = null;
		
		switch (category) {
			case "#액션":
				categoryName = "1";
				break;
			case "#로맨스":
				categoryName = "2";
				break;
			case "#추리":
				categoryName = "3";
				break;
			case "#에세이":
				categoryName = "4";
				break;
			case "#교육":
				categoryName = "5";
				break;
			case "#소설":
				categoryName = "6";
				break;
			case "#기술":
				categoryName = "7";
				break;
			case "#인문":
				categoryName = "8";
				break;
			case "#경영":
				categoryName = "9";
				break;
			case "#여행":
				categoryName = "10";
				break;
			case "#요리":
				categoryName = "11";
				break;
			case "#건강":
				categoryName = "12";
				break;
			default:
				categoryName = "";
				break;
		}		
		return categoryName;
	}
}
