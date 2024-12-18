package _4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("owner")
public class OwnerController {
	@GetMapping("form")
	public String form() {
		return "thymeleaf/owner/form";
	}
}
