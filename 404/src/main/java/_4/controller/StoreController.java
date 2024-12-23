package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.command.StoreApplicationCommand;
import _4.command.StoreCommand;
import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import _4.service.store.StoreApplyService;
import _4.service.store.StoreInfoModifyService;
import _4.service.store.StoreInfoService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("store")
public class StoreController {
	@Autowired
	StoreInfoService storeInfoService;
	@Autowired
	StoreApplyService storeApplyService;
	@Autowired
	StoreInfoModifyService storeInfoModifyService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	StoreMapper storeMapper;
		
	@PostMapping("storeApply")
	public String storeForm(StoreApplicationCommand storeApplicationCommand,  HttpSession session) {
		String ownerNum = userNumService.execute(session);
		storeApplyService.execute(storeApplicationCommand, session, ownerNum);
		return "thymeleaf/owner/storeApplyFinished";
	}
	
	@GetMapping("storeMainPage")
	public String storeMainPage(Model model, @RequestParam String ownerNum) {
		storeInfoService.execute(model, ownerNum);
		return "thymeleaf/store/storeMainPage";
	}
	
	@PostMapping("storeInfoModify")
	public void storeInfoModify(StoreCommand storeCommand) {
		storeInfoModifyService.execute(storeCommand);
	}
	
}
