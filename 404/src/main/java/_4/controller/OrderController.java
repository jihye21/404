package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.BookCommand;
import _4.domain.BookDTO;
import _4.domain.ThemeDTO;
import _4.mapper.BookMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.book.ThemeBookInsertService;
import _4.service.purchase.IniPayReqService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	ThemeMapper themeMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	ThemeBookInsertService themeBookInsertService;
	
	@PostMapping("themeOrder")
	public String themeOrder(String themeNum, String themeTime, Model model) {
		ThemeDTO themeDTO = themeMapper.themeSelectOne(themeNum);
		model.addAttribute("themeDTO", themeDTO);
		model.addAttribute("themeTime", themeTime);
		return "thymeleaf/order/themeOrderPage";
	}
	
	@PostMapping("payment")
	public String payment(BookCommand bookCommand, Model model, HttpSession session) {
		String bookNum = themeBookInsertService.execute(bookCommand, session);
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		iniPayReqService.execute(dto, model);
		return "thymeleaf/purchase/payment";
	}
}
