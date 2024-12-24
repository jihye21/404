package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.command.InquireCommand;
import _4.domain.AuthDTO;
import _4.service.inqurie.InquireCheckService;
import _4.service.inqurie.InquireDeleteService;
import _4.service.inqurie.InquireDetailService;
import _4.service.inqurie.InquireListService;
import _4.service.inqurie.InquireRegistService;
import _4.service.inqurie.InquireUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireDeleteService inquireDeleteService;
	@Autowired
	InquireCheckService inquireCheckService;
	@Autowired
	InquireUpdateService inquireUpdateService;
	@Autowired
	InquireDetailService inquireDetailService;
	@Autowired
	InquireListService inquireListService;
	@Autowired
	InquireCommand inquireCommand;
	@Autowired
	InquireRegistService inquireRegistService;
	
	//문의하기
	@GetMapping("inquireList")
	public String inquireList(Model model, HttpSession session) {
		inquireListService.execute(model, session);
		return "thymeleaf/inquire/inquireList";
	}
	
	@GetMapping("inquirePage")
	public String inquirePage() {
		return "thymeleaf/inquire/inquirePage";
	}
	
	@PostMapping("inquireRegist")
	public String inquireRegist(InquireCommand inquireCommand, HttpSession session) {
		inquireRegistService.execute(inquireCommand, session);
		return "redirect:inquireList";
	}
	
	@GetMapping("inquireDetail")
	public String inquireDetail(Model model, HttpSession session, @RequestParam String inquireNum) {
		inquireDetailService.execute(model, session, inquireNum);
		return "thymeleaf/inquire/inquireDetail";
	}
	
	@GetMapping("inquireModify")
	public String inquireModify(Model model, HttpSession session, @RequestParam String inquireNum) {
		//문의에 답변이 있는지 확인하는 서비스
		String result = inquireCheckService.execute(session, inquireNum);
		
		//답변한 직원이 있다면 수정이 불가능하도록 함.
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(result != null) {
			if(auth.getGrade().equals("employee")) {
				inquireDetailService.execute(model, session, inquireNum);
				return "thymeleaf/inquire/inquireModify";
			}
			//답변한 직원이 없으면 문의 상세 페이지로 이동하기
			return "redirect:inquireDetail?inquireNum="
					+ inquireNum;
		}else if(result == null){
			//답변한 직원이 없으면 문의 update 하기
			inquireDetailService.execute(model, session, inquireNum);
		}
		
		return "thymeleaf/inquire/inquireModify";
	}
	@PostMapping("inquireModify")
	public String inquireModify(InquireCommand inquireCommand, HttpSession session) {
		inquireUpdateService.execute(inquireCommand, session);
		return "redirect:inquireDetail?inquireNum="
				+ inquireCommand.getInquireNum();
	}
	//삭제하기
	@GetMapping("inquireDelete")
	public String inquireDelete(@RequestParam String inquireNum, HttpSession session) {
		//문의에 답변이 있는지 확인하는 서비스
		String result = inquireCheckService.execute(session, inquireNum);
		
		//답변한 직원이 있다면 삭제가 불가능하도록 함.
		if(result != null) {
			//답변한 직원이 없으면 문의 상세 페이지로 이동하기
			return "redirect:inquireDetail?inquireNum="
					+ inquireNum;
		}else if(result == null){
			//답변한 직원이 없으면 문의 delete 하기
			inquireDeleteService.execute(session, inquireNum);
		}
		
		return "redirect:/inquireList";
	}
	//@@@@@@@@@@@@@@@@@@
	
	
	
}
