package _4.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.domain.BookDTO;
import _4.domain.ReviewDTO;
import _4.mapper.BookMapper;
import _4.mapper.ReviewMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	ReviewMapper reviewMapper;
	
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
		ReviewDTO reviewDTO = reviewMapper.reviewSelectOneWithBookNum(bookNum);
		model.addAttribute("dto", dto);
		model.addAttribute("reviewDTO", reviewDTO);
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
	
	@PostMapping("registThemeTime")
	public String registThemeTime(String bookNum, String themeTime) {
		// DB 변경하기
		if(themeTime.equals("바로예약")) {
			LocalTime now = LocalTime.now();          
			int hour = now.getHour();       
			int minute = now.getMinute();       
			if(minute >= 45) {
				minute = minute - 60 + 15;
			}
			else {
				minute += 15;
			}
			themeTime = hour + ":" + minute;

			bookMapper.themeTimeUpdate(bookNum, themeTime);
		}
		else {
			bookMapper.themeTimeUpdate(bookNum, themeTime);
		}
		return "redirect:/book/memberBookDetail?bookNum=" + bookNum;
	}
	
	@PostMapping("bookReceipt")
	public String bookReceipt(@RequestParam("bookNum") String bookNum, Model model) {
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/book/bookReceipt";
	}
	
	@PostMapping("bookOk")
	public @ResponseBody void bookOk(@RequestBody @RequestParam("bookNum") String bookNum) {
		String bookStatus = "예약완료";
		bookMapper.bookStatusUpdate(bookNum, bookStatus);
	}
	
	@PostMapping("bookNo")
	public @ResponseBody void bookNo(@RequestParam("bookNum") String bookNum) {
		String bookStatus = "예약취소";
		bookMapper.bookStatusUpdate(bookNum, bookStatus);
	}
	
	@PostMapping("bookFinished")
	public @ResponseBody void bookFinished(@RequestParam("bookNum") String bookNum, @RequestParam("finalPrice") String finalPrice) {
		String bookStatus = "방문완료";
		bookMapper.bookStatusUpdate(bookNum, bookStatus);
		bookMapper.bookFinalPriceUpdate(bookNum, finalPrice);
	}
}
