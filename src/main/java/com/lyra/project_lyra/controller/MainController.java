package com.lyra.project_lyra.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lyra.project_lyra.entity.member.MemberInfo;
import com.lyra.project_lyra.repository.member.MemberInfoRepository;
import com.lyra.project_lyra.service.interfaces.BookService;
import com.lyra.project_lyra.service.interfaces.MemberService;

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
	public ModelAndView getMainList(HttpServletRequest request, Model model,@PathVariable("data") String category) throws Exception{
		String username = category;
		String[] categoryOne = memberService.getCategory(username);
		
		model.addAttribute("bookResult", categoryNameChange(username));
		model.addAttribute("result1", bookService.getCategoryList(categoryOne[0]));
		model.addAttribute("result2", bookService.getCategoryList(categoryOne[1]));
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/main");
        return modelAndView;
	}
	
	@PostMapping("/main")
	public String getMainList(Authentication authentication) throws Exception{
		String username = (String) authentication.getPrincipal();

		return username;
	}
	
	@PostMapping("/categoryInsert")
	public void setCategoryInsert() {
		log.info("categoryInsert Test");
	}
	
	public String[] categoryNameChange(String username) {
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
}
