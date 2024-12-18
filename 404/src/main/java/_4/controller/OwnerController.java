package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.OwnerCommand;
import _4.service.owner.OwnerRegistService;


@Controller
@RequestMapping("owner")
public class OwnerController {
	
	@Autowired
	OwnerRegistService ownerRegistService;
	
	@Autowired
	OwnerCommand ownerCommand;
	
	@GetMapping("ownerForm")
	public String ownerForm() {
		return "thymeleaf/owner/ownerForm";
	}
	
	@PostMapping("ownerForm")
	public String insert(OwnerCommand ownerCommand) {
		ownerRegistService.execute(ownerCommand);
		return "redirect:ownerForm";
	}
}
