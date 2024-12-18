package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.service.OwnerService;

@Controller
@RequestMapping("owner")
public class OwnerController {
	@Autowired
	OwnerService ownerService;
	
	@GetMapping("ownerForm")
	public String ownerForm() {
		return "thymeleaf/owner/ownerForm";
	}
	
	@PostMapping("ownerForm")
	public String insert() {
		ownerService.execute();
		return "redirect:ownerForm";
	}
}
