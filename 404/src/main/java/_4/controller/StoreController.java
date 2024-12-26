package _4.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import _4.service.member.WishCheckService;
import _4.service.store.StoreApplyService;
import _4.service.store.StoreInfoModifyService;
import _4.service.store.StoreInfoService;
import _4.service.theme.ThemeListService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("store")
public class StoreController {
	@Autowired
	WishCheckService wishCheckService;
	@Autowired
	StoreInfoService storeInfoService;
	@Autowired
	StoreApplyService storeApplyService;
	@Autowired
	ThemeListService themelistService;
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
	public String storeMainPage(Model model, @RequestParam String ownerNum, HttpSession session) {
		StoreDTO storeDTO = storeInfoService.execute(model, ownerNum);
		wishCheckService.execute(storeDTO, session, model);
		themelistService.execute(storeDTO.getStoreNum(), model);
		return "thymeleaf/store/storeMainPage";
	}
	
	@PostMapping("storeInfoModify")
	public String storeInfoModify(StoreCommand storeCommand) {
		storeInfoModifyService.execute(storeCommand);
		return "redirect:/owner/ownerMainPage";
	}
	
}
