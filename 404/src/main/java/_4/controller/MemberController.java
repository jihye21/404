package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.MemberCommand;
import _4.service.member.MemberDetailService;
import _4.service.member.MemberRegistService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberRegistService memberRegistService;
	@Autowired
	MemberDetailService memberDetailService;
	
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String memberRegist() {
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand) {
		memberRegistService.execute(memberCommand);
		 return "";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(String memNum, Model model) {
		return "thymeleaf/member/memInfo";
	}
}
