package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.WaitNumCommand;
import _4.domain.WaitNumDTO;
import _4.mapper.BookMapper;
import _4.mapper.StoreMapper;
import _4.service.book.CallMemberService;
import _4.service.book.WaitNumInsertService;
import _4.service.book.WaitNumService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("wait")
public class WaitNumController {
	@Autowired
	WaitNumService waitNumService;
	@Autowired
	WaitNumInsertService waitNumInsertService;
	@Autowired
	CallMemberService callMemberService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	StoreMapper storeMapper;
	
	@PostMapping("getWaitNum")
	public @ResponseBody Integer getWaitingNumber(String storeNum, HttpSession session) {
		Integer waitNum = waitNumService.execute(storeNum);
		return waitNum;
	}
	
	@PostMapping("setWaitNum")
	public String setWaitNum(WaitNumCommand waitNumCommand) {
		waitNumInsertService.execute(waitNumCommand);
		return "redirect:/book/memberBookDetail?bookNum=" + waitNumCommand.getBookNum();
	}
	
	@PostMapping("waitNumList")
	public String waitNumList(String storeNum, Model model) {
		List<WaitNumDTO> list = storeMapper.waitNumSelectAll(storeNum);
		model.addAttribute("list", list);
		return "thymeleaf/book/waitNumList";
	}
	
	@PostMapping("callWaitNum")
	public @ResponseBody void callWaitNum(String bookNum) {
		callMemberService.execute(bookNum);
	}
	
	@PostMapping("waitNumDelete")
	public @ResponseBody void waitNumDelete(String bookNum) {
		storeMapper.waitNumDelete(bookNum);
	}
	
}
