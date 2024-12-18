package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.service.member.MemberRegistService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberRegistService memberRegistService; 
	
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String memberRegist() {
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist1() {
		memberRegistService.execute();
		 return "로그인 페이ert지";
	}
}
