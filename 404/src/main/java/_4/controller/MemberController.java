package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.MemberCommand;
import _4.mapper.service.UserNumService;
import _4.service.member.MemberDetailService;
import _4.service.member.MemberRegistService;
import _4.service.member.MemberUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberRegistService memberRegistService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	UserNumService userNumService;
	
	@GetMapping("memberMainPage")
	public String memberMainPage(HttpSession session, Model model) {
		String memNum = userNumService.execute(session);
		model.addAttribute("memNum", memNum);
        return "thymeleaf/member/memberMainPage";
	}
	
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String memberRegist(Model model) {
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist(@Validated MemberCommand memberCommand) {
		memberRegistService.execute(memberCommand);
		 return "redirect:/";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(String memNum, Model model, MemberCommand memberCommand) {
		memberDetailService.execute(memNum, model, memberCommand);
		return "thymeleaf/member/memberInfo";	// 내 정보 보기
	}
	
	@GetMapping("memberModify")
	public String memberModify(MemberCommand memberCommand, Model model) {
		return "thymeleaf/member/memberModify";		// 내 정보 수정
	}
	
	@PostMapping("memberModify")
	public String memberUpdate(MemberCommand memberCommand, Model model) {
		memberUpdateService.execute(memberCommand, model);
		return "redirect:memberDetail?memNum=" + memberCommand.getMemNum();
	}
	
	
	
}
