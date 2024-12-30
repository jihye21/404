package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	
	@GetMapping("memberBookList")
	public String memberBookList(HttpSession session, Model model) {
		String memberNum = userNumService.execute(session);
		List<BookDTO> list = bookMapper.bookSelectAllWithMember(memberNum);
		model.addAttribute("list", list);
		return "thymeleaf/book/memberBookList";
	}
	@GetMapping("memberBookDetail")
	public String memberBookDetail(String bookNum, Model model) {
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/book/memberBookDetail";
	}
	
	@PostMapping("timeBook")
	public String timeBook(@RequestParam("bookNum") String bookNum, Model model) {
		model.addAttribute("bookNum", bookNum);
		return "thymeleaf/book/timeBook";
	}
	
	@PostMapping("directBook")
	public String directBook(@RequestParam("bookNum") String bookNum, Model model) {
		model.addAttribute("bookNum", bookNum);
		return "thymeleaf/book/directBook";
	}
}
