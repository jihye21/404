package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.DepositCommand;
import _4.command.StoreApplicationCommand;
import _4.command.StoreCommand;
import _4.domain.AuthDTO;
import _4.domain.StoreDTO;
import _4.mapper.BookMapper;
import _4.mapper.MainMapper;
import _4.mapper.StoreApplMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import _4.service.member.WishCheckService;
import _4.service.store.DepositSettingService;
import _4.service.store.StoreApplyService;
import _4.service.store.StoreInfoModifyService;
import _4.service.store.StoreInfoService;
import _4.service.theme.ThemeListService;
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
	DepositSettingService depositSettingService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	StoreApplMapper storeApplMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	MainMapper mainMapper;
	
	@PostMapping("bussRegNumCheck")
	public @ResponseBody String bussRegNumCheck(String bussRegNum) {
		String result = storeApplMapper.bussRegNumCheck(bussRegNum);
		return result;
	}
	
	@PostMapping("storeList")
	public String storeList(Model model) {
		List<StoreDTO> list = storeMapper.storeSelectAll();
		model.addAttribute("list", list);
		return "thymeleaf/course/storeTable";
	}
	
	@PostMapping("storeSearch")
	public String storeSearch(String storeName, Model model) {
		List<StoreDTO> list = storeMapper.storeSearch(storeName);
		model.addAttribute("list", list);
		return "thymeleaf/course/storeTable";
	}
	
	@PostMapping("storeApply")
	public String storeForm(StoreApplicationCommand storeApplicationCommand,  HttpSession session) {
		String ownerNum = userNumService.execute(session);
		storeApplyService.execute(storeApplicationCommand, session, ownerNum);
		return "thymeleaf/owner/storeApplyFinished";
	}
	
	
	@GetMapping("storeMainPage")
	public String storeMainPage(Model model, @RequestParam String ownerNum, HttpSession session) {
		StoreDTO storeDTO = storeInfoService.execute(model, ownerNum);
		
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth != null) {
			String memberNum = userNumService.execute(session);
			bookMapper.waitedBookDelete(memberNum);
		}
		
		String storeNum = storeDTO.getStoreNum();
		wishCheckService.execute(storeDTO, session, model);
		themelistService.execute(storeDTO.getStoreNum(), model);
		Integer wishCount = mainMapper.wishCountSelect(storeNum);
		model.addAttribute("wishCount", wishCount);
		return "thymeleaf/store/storeMainPage";
	}
	
	@PostMapping("storeInfoModify")
	public String storeInfoModify(StoreCommand storeCommand, DepositCommand depositCommand, HttpSession session) {
		storeInfoModifyService.execute(storeCommand, session);
		if(depositCommand.getStartPrice() != null)	depositSettingService.execute(depositCommand, session);
		return "redirect:/owner/ownerMainPage";
	}
	
}
