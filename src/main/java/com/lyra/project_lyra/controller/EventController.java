package com.lyra.project_lyra.controller;


import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
//event라는 주소로 묶어서 사용
@RequestMapping("/event")
// 생성자 주입방식으로 의존성을 주입받게된다.
@RequiredArgsConstructor
@Log4j2
public class EventController {
	
	private final EventService eventService;
	
	@GetMapping("/paging")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<EventDTO> eventList = eventService.paging(pageable);
		
		// page 갯수 20개 = 글 갯수 60개
		// 현재 사용자가 3페이지면 페이지가 3 4 5만 보여지도록 처리.
		int blockLimit = 3; 
		// 현재 사용자가 1 또는 2, 3 페이지에있으면 1을 주도록.
		int startPage = 
				(((int)(Math.ceil((double)pageable.getPageNumber() 
						/ blockLimit))) - 1)  * blockLimit + 1;
		/* 현재 사용자가 7 또는 8, 9 페이지에 있으면 7이라는 값을 만들어주도록.
		 9보다 실제 페이지 갯수가 작은 값을 가지고있다면 9라는 값을 보여주지말고 
		 전체페이지 값을 endpage값으로 하라는 의미 
		 */
		log.info(eventList);
		
		
		int endPage = 
				((startPage + blockLimit - 1) < eventList.getTotalPages())
				? startPage + blockLimit - 1 : eventList.getTotalPages();
		model.addAttribute("eventList", eventList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		
		log.info("))>><<LKJA"+model.addAttribute("eventList", eventList));
		
		
		return "event/paging";
	}
	
	
	/*버튼(html)이나 location(script)으로 요청하는 방식은 GetMapping이다.
	 save페이지요청이 오면 saveForm메소드를 찾아갈 수 있도록 리턴*/
	@GetMapping("/save")
	public String registerForm() {
		return "event/save";	
	}
	
	/* @RequestParam : html에서 입력한 값을 Controller로 간단하게 전달가능 */
	@PostMapping("/save")
	public String register(@ModelAttribute EventDTO eventDTO, RedirectAttributes redirectAttribute) throws IOException {
		System.out.println("eventDTO => " + eventDTO);
		eventService.register(eventDTO);
		return "redirect:/event/paging";
	}
	
	
	//게시글 번호를 받아왔을때 호출되는 메소드.
	// 페이징 처리 추가. 페이지 요청이 없는경우도 있으니 Pageable도 받아주도록.
	@GetMapping("/{evnum}")
	public  String read(@PathVariable Long evnum, Model model,
						   @PageableDefault(page=1) Pageable pageable) {
		/* 
		 해당 게시글 조회수를 하나 올리고
		 게시글 데이터를 가져와서 detail.html에 출력
		 */
		eventService.updateHits(evnum);
		EventDTO eventDTO = eventService.findById(evnum);
		/* 댓글 목록 가져오기 */
		
		model.addAttribute("event", eventDTO);
		// DB에다가 페이징을 요청할 필요없이 화면으로갈때 몇페이지에서 넘어온건지만 필요하기에
		model.addAttribute("page", pageable.getPageNumber());
		
		
		return "event/detail";
	}
	
	// 게시글의 정보만 필요하며, 업데이트html에다가 정보를 보여주는게 updateForm메소드의 목적
	@GetMapping("/update/{evnum}")
	public String updateForm(@PathVariable Long evnum, Model model) {
		EventDTO eventDTO = eventService.findById(evnum);
		model.addAttribute("eventUpdate", eventDTO);
		return "event/update";
	}
	
	// Update를 html에 전달해주는 메소드 생성
	// 수정이 반영된 상세페이지를 보여주도록
	@PostMapping("/update")
	public String update(@ModelAttribute EventDTO eventDTO, Model model) {
		EventDTO event = eventService.update(eventDTO);
		model.addAttribute("event", event);
//		return "paging";
		return "redirect:/event/" + eventDTO.getEvnum();
	}
	
	// 게시글 삭제
	@GetMapping("/delete/{evnum}")
	public String delete(@PathVariable Long evnum) {
		eventService.delete(evnum);
		return "redirect:/event/paging";
	}
	
	// 페이징 목록, event/paging?page = 1 

}

