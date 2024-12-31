package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.OwnerCommand;
import _4.domain.BookDTO;
import _4.domain.StoreDTO;
import _4.mapper.BookMapper;
import _4.mapper.StoreMapper;
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
	StoreMapper storeMapper;
	@Autowired
	BookMapper bookMapper;
	
	@GetMapping("ownerForm")
	public String ownerForm(Model model) {
		OwnerCommand ownerCommand = new OwnerCommand();
		model.addAttribute("ownerCommand", ownerCommand);
		return "thymeleaf/owner/ownerForm";
	}
	
	@PostMapping("ownerForm")
	public String ownerForm(OwnerCommand ownerCommand) {
		ownerRegistService.execute(ownerCommand);
		return "redirect:/";
	}
	
	@GetMapping("ownerMainPage")
	public String ownerMainPage(HttpSession session, Model model) {
		String ownerNum = userNumService.execute(session);
		StoreDTO dto = storeMapper.storeSelectOne(ownerNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/owner/ownerMainPage";
	}
	
	
	
	@PostMapping("storeInfoManagePage")
	public String storeInfoManage(HttpSession session, Model model) {
		String ownerNum = userNumService.execute(session);
		StoreDTO dto = storeMapper.storeSelectOne(ownerNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/store/ownerView/storeInfoManagePage";
	}
	
	@PostMapping("bookManagePage")
	public String bookManagePage(HttpSession session, Model model) {
		String ownerNum = userNumService.execute(session);
		String storeNum = storeMapper.storeNumSelect(ownerNum);
		List<BookDTO> list = bookMapper.bookSelectAllWithStore(storeNum);
		model.addAttribute("list", list);
		return "thymeleaf/store/ownerView/bookManagePage";
	}
	
	@PostMapping("reviewManagePage")
	public String reviewManagePage() {
		return "thymeleaf/store/ownerView/reviewManagePage";
	}
	
}
