package _4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MainController {
	
	@GetMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	
}
