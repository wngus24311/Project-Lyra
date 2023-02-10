package com.lyra.project_lyra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lyra.project_lyra.dto.EventImageDTO;
import com.lyra.project_lyra.service.interfaces.EventImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/event")
@Log4j2
@RequiredArgsConstructor
public class EventImageController {
	
	private final EventImageService eventImageService;
	
	@GetMapping("/register")
	public void imageRegister() {
		
	}
	@PostMapping("/register")
	public String imageRegister(EventImageDTO eventImageDTO, RedirectAttributes redirectAttribute) {
		log.info("===== Review Register =====");
		log.info("ReviewDTO => " + eventImageDTO);
		
		Long eventReviewNum = eventImageService.register(eventImageDTO);
		redirectAttribute.addFlashAttribute("msg", eventReviewNum);
		
		return "redirect:/event/eventimage";
	}
	
	@GetMapping("/imagelist")
	public void imageList(Long imageNum, Model model) {
		log.info("===== ImageList =====");
		model.addAttribute("result", eventImageService.getEventImageList(imageNum));
	}
	
	@GetMapping({"/read", "/modify"})
	public void imageRead(Long imageNum, Model model) {
		log.info("===== Image Read =====");
		log.info("EventNum =>" + imageNum);
		
		EventImageDTO eventImageDTO = eventImageService.read(imageNum);
		model.addAttribute("dto", eventImageDTO);
	}
	
	@PostMapping("/modify")
	public String imageModify(EventImageDTO eventImageDTO, RedirectAttributes redirectAttribute) {
		log.info("===== Image Modify =====");
		log.info("");
	}
	
	
	
}
