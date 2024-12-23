package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.service.help.FindIdService;
import _4.service.help.FindPwService;

@Controller
public class HelpController {
	@Autowired
	FindIdService findIdService;
	@Autowired
	FindPwService findPwService;
	
	@GetMapping("findId")
	public String findId() {
		return "thymeleaf/help/findId";
	}
	
	@PostMapping("findId")
	public String findId(
			@RequestParam("userName") String userName,
			@RequestParam("userPhone") String userPhone,
			Model model) {
		findIdService.execute(userName, userPhone, model);
		return "thymeleaf/help/findIdOk";
	}
	
	@GetMapping("findPw")
	public String findPw() {
		return "thymeleaf/help/findPw";
	}
	
	@PostMapping("findPw")
	public String findPw(
			@RequestParam("userId") String userId,
			@RequestParam("userPhone") String userPhone,
			Model model) {
		findPwService.execute(userId, userPhone, model);
		return "thymeleaf/help/findPwOk";
	}
}











