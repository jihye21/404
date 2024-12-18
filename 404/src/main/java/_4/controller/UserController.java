package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.UserCommand;
import _4.service.user.LoginService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	LoginService loginService;
	@GetMapping("loginForm")
	public String loginForm() {
		return "thymeleaf/user/loginForm";
	}
	
	@PostMapping("login")
	public String login(UserCommand userCommand, BindingResult result) {
		loginService.execute(userCommand, result);
		return "redirect:/";
	}
}
