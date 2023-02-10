package com.lyra.project_lyra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/event")
@Log4j2
@RequiredArgsConstructor
public class EventController {
	
	private final EventService eventService;
	
	@GetMapping("/register")
	public void eventRegister() {
		
	}
	
	@PostMapping("/register")
	public String eventRegister(EventDTO eventDTO, RedirectAttributes redirectAttributes ) {
		log.info("===== Event Register =====");
		log.info("EventDTO => " + eventDTO);
		
		Long eventNum = eventService.register(eventDTO);
		redirectAttributes.addFlashAttribute("msg", eventNum);
		
		return "redirect:/event/eventlist";
	}
	
	@GetMapping("/list")
	public void eventList(Long eventNum, Model model) {
		log.info("===== EventList =====");
		model.addAttribute("result", eventService.getEventList(eventNum));
	}
	
	@GetMapping({"/read", "/modify"})
	public void eventRead(Long eventNum, Model model) {
		log.info("===== Event Read =====");
		log.info("EventNum =>" + eventNum);
		
		EventDTO eventDTO = eventService.read(eventNum);
		model.addAttribute("dto", eventDTO);
	}
	
	@PostMapping("/modify")
	public String eventModify(EventDTO eventDTO, RedirectAttributes redirectAttributes) {
		log.info("===== Event Modify =====");
		log.info("EventDTO => " + eventDTO);
		
		eventService.modify(eventDTO);
		
		redirectAttributes.addAttribute("eventNum", eventDTO.getEventName());
		return "redirect:/event/read";
	}
	
	@PostMapping("/remove")
	public String remove(Long eventNum, RedirectAttributes redirectAttributes) {
		log.info("===== Event Remove =====");
		eventService.remove(eventNum);
		redirectAttributes.addFlashAttribute("msg", eventNum);
		return "redirect:/event/list";
	}
	
	
}