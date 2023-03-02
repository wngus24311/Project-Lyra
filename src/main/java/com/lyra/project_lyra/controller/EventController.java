package com.lyra.project_lyra.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lyra.project_lyra.dto.EventDTO;
import com.lyra.project_lyra.service.interfaces.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Log4j2
public class EventController {
	
	private final EventService eventService;
	
	@GetMapping("/paging")
	public ModelAndView paging(@PageableDefault(page = 1) Pageable pageable, Model model,
			@RequestParam(value = "name", required=false) String loginUser) {
		Page<EventDTO> eventList = eventService.paging(pageable);

		int blockLimit = 3; 
		int startPage = 
				(((int)(Math.ceil((double)pageable.getPageNumber() 
						/ blockLimit))) - 1)  * blockLimit + 1;

		log.info(eventList);
		String username;
		if (loginUser == null) {
			username = "user";
		} else {
			username = loginUser;
		}
		
		int endPage = 
				((startPage + blockLimit - 1) < eventList.getTotalPages())
				? startPage + blockLimit - 1 : eventList.getTotalPages();
		model.addAttribute("eventList", eventList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		log.info("eventList" + model.addAttribute("eventList", eventList));
		log.info("startPage" + model.addAttribute("startPage", startPage));
		log.info("endPage" + model.addAttribute("endPage", endPage));
		
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/event/paging");
        
        return modelAndView;
	}
	
    @PostMapping("/paging")
	public String paging(Authentication authentication) {
    	String username = (String)authentication.getPrincipal();

		return username;
	}

	@GetMapping("/save")
	public String registerForm() {
		return "event/save";	
	}
	
	@PostMapping("/save")
	public String register(@ModelAttribute EventDTO eventDTO, RedirectAttributes redirectAttribute) throws IOException {
		System.out.println("eventDTO => " + eventDTO);
		eventService.register(eventDTO);
		return "redirect:/event/paging";
	}
	
	@GetMapping("/{evnum}")
	public  ModelAndView read(@PathVariable Long evnum, Model model,
						   @PageableDefault(page=1) Pageable pageable,
						   @RequestParam(value = "name", required=false) String loginUser) {

		eventService.updateHits(evnum);
		EventDTO eventDTO = eventService.findById(evnum);
		/* ��� ��� �������� */
		List<EventDTO> eventReviewDTOList = eventService.reviewFindAll(evnum);
		
		model.addAttribute("eventReviewList", eventReviewDTOList);
		model.addAttribute("event", eventDTO);
		// DB���ٰ� ����¡�� ��û�� �ʿ���� ȭ�����ΰ��� ������������ �Ѿ�°����� �ʿ��ϱ⿡
		model.addAttribute("page", pageable.getPageNumber());
    	String username;
		log.info(loginUser);
		if (loginUser == null) {
			username = "user";
		}else {
			username = loginUser;			
		}	
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/event/detail");
        return modelAndView;
	}
	
    
    @PostMapping("/{evnum}")
	public String read(Authentication authentication) {
    	String username = (String)authentication.getPrincipal();

		return username;
	}
	
	
	// �Խñ��� ������ �ʿ��ϸ�, ������Ʈhtml���ٰ� ������ �����ִ°� updateForm�޼ҵ��� ����
	@GetMapping("/update/{evnum}")
	public String updateForm(@PathVariable Long evnum, Model model) {
		EventDTO eventDTO = eventService.findById(evnum);
		model.addAttribute("eventUpdate", eventDTO);
		return "event/update";
	}
	
	// Update�� html�� �������ִ� �޼ҵ� ����
	// ������ �ݿ��� ���������� �����ֵ���
	@PostMapping("/update")
	public String update(@ModelAttribute EventDTO eventDTO, Model model) {
		EventDTO event = eventService.update(eventDTO);
		model.addAttribute("event", event);
//		return "paging";
		return "redirect:/event/" + eventDTO.getEvnum();
	}
	
	// �Խñ� ����
	@GetMapping("/delete/{evnum}")
	public String delete(@PathVariable Long evnum) {
		eventService.delete(evnum);
		return "redirect:/event/paging";
	}
	
	@ResponseBody
	@PostMapping("/review/register")
    public ResponseEntity<List<EventDTO>> register(@ModelAttribute EventDTO eventDTO, 
    		@PageableDefault(page=1) Pageable pageable, Authentication authentication) {
        System.out.println("commentDTO = " + eventDTO);
        Long evrnum = eventService.reviewRegister(eventDTO);
        
        String username = (String)authentication.getPrincipal();
        
        log.info("�̺�Ʈ ���� ��ȣ =========>" + evrnum);
        
        List<EventDTO> eventReviewDTOList = eventService.reviewFindAll(eventDTO.getEvnum());
        log.info("�̺�Ʈ ���� ����Ʈ =========>" + eventReviewDTOList);
        if (evrnum != null) {
            log.info("===========ReviewList===========" + eventReviewDTOList);
            return new ResponseEntity<>(eventReviewDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

	 @DeleteMapping("/{evrnum}") public ResponseEntity<String> reviewDelete(@PathVariable("evrnum") Long evrnum) {
		 System.out.println("��� ���� ��ȣ" + evrnum);
		 eventService.reviewDelete(evrnum);
	  
		 return new ResponseEntity<String>("success", HttpStatus.OK); }

}
	// ����¡ ���, event/paging?page = 1 

