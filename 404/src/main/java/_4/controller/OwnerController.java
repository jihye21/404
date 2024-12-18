package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String ownerForm(Model model) {
		OwnerCommand ownerCommand = new OwnerCommand();
		model.addAttribute("ownerCommand", ownerCommand);
		return "thymeleaf/owner/ownerForm";
	}
	
	@PostMapping("ownerForm")
	public String insert(@Validated OwnerCommand ownerCommand, Model model, BindingResult result) {
		
		if(result.hasErrors()) {
			return "thymeleaf/owner/ownerForm";
		}
		
		ownerRegistService.execute(ownerCommand, model);
		
		return "redirect:/ownerForm";
	}
}
