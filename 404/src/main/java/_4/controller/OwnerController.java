package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.OwnerCommand;
import _4.mapper.OwnerMapper;
import _4.mapper.service.UserNumService;
import _4.service.owner.OwnerRegistService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("owner")
public class OwnerController {
	
	@Autowired
	OwnerRegistService ownerRegistService;
	@Autowired
	UserNumService userNumService;
	
	@Autowired
	OwnerMapper ownerMapper;
	
	@GetMapping("ownerForm")
	public String ownerForm(Model model) {
		OwnerCommand ownerCommand = new OwnerCommand();
		model.addAttribute("ownerCommand", ownerCommand);
		return "thymeleaf/owner/ownerForm";
	}
	
	@PostMapping("ownerForm")
	public String ownerForm(OwnerCommand ownerCommand) {
		/*if(result.hasErrors()) {
			return "thymeleaf/owner/ownerForm";
		}*/
		ownerRegistService.execute(ownerCommand);
		return "redirect:/";
	}
	
	@GetMapping("ownerMainPage")
	public String ownerMainPage(HttpSession session) {
		String ownerNum = userNumService.execute(session);
		ownerMapper.sele
		return "thymeleaf/owner/ownerMainPage";
	}
	
	
}
