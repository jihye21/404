package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.StoreCommand;
import _4.service.store.StoreRegistService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("store")
public class StoreController {
	
	@Autowired
	StoreRegistService storeRegistService;
	
	@GetMapping("storeForm")
	public String storeForm() {
		return "thymeleaf/store/storeForm";
	}
	
	@PostMapping("storeForm")
	public String storeForm(StoreCommand storeCommand) {
		storeRegistService.execute(storeCommand);
		return "redirect:storeForm";
	}
	
}
