package com.lyra.project_lyra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor 
public class MainController {
	private final BookService bookService;
	
	@GetMapping("/main")
	public void getMainList(Model model) {
		log.info("mainpage : ");
		
		log.info("list test : " + bookService.getList());
		
		model.addAttribute("result", bookService.getList());
	}
	
}
