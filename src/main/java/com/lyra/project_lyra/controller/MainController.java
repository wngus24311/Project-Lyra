package com.lyra.project_lyra.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.interfaces.BookService;
import com.lyra.project_lyra.service.interfaces.MemberService;
import com.lyra.project_lyra.util.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor
public class MainController {
	private final BookService bookService;
	private final MemberService memberService;
	private final MemberInfoRepository repository;

	@GetMapping("/main/{data}")
	public ModelAndView getMainList(Model model, @PathVariable("data") String loginUser) throws Exception{
		String username = loginUser;
		String[] categoryOne = memberService.getCategory(username);
		
		model.addAttribute("username", username);
		model.addAttribute("bookResult", categoryDBtoView(username));
		model.addAttribute("result1", bookService.getCategoryList(categoryOne[0]));
		model.addAttribute("result2", bookService.getCategoryList(categoryOne[1]));
		model.addAttribute("bookLikeList", bookService.getLikeList());
		model.addAttribute("bookUpdateList", bookService.getUpdateList());
		
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
	public void setCategoryInsert(Model model, @RequestBody Map<String,Object> data) {
		String category = (String)data.get("category");
		String url = (String)data.get("url");
		
		String[] usernames = url.split("http://localhost:8095/main/main/");
		String categoryEntity = categoryViewtoDB(category);
		String username = usernames[1];
		
		memberService.categoryInsert(username, categoryEntity);
		log.info("category Insert Success");
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
