package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.StoreCommand;
import _4.mapper.service.UserNumService;
import _4.service.store.StoreApplyService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("store")
public class StoreController {
	@Autowired
	StoreApplyService storeApplyService;
	@Autowired
	UserNumService userNumService;
		
	@PostMapping("storeApply")
	public String storeForm(StoreCommand storeCommand, HttpSession session) {
		String ownerNum = userNumService.execute(session);
		storeApplyService.execute(storeCommand, session, ownerNum);
		return "thymeleaf/owner/storeApplyFinished";
	}
}
