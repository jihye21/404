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

import _4.command.BookCommand;
import _4.domain.BookDTO;
import _4.domain.ReviewDTO;
import _4.mapper.BookMapper;
import _4.mapper.ReviewMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import _4.service.book.WaitNumService;
import _4.service.member.MemberSavePointService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	MemberSavePointService memberSavePointService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	WaitNumService waitNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	StoreMapper storeMapper;
	
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
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		String storeCrowded = storeMapper.storeSelectOne(dto.getStoreNum()).getStoreCrowded();
		model.addAttribute("storeCrowded", storeCrowded);
		model.addAttribute("dto", dto);
		return "thymeleaf/book/directBook";
	}
	
	@PostMapping("getWaitNum")
	public @ResponseBody Integer getWaitingNumber(String storeNum, HttpSession session) {
		Integer waitNum = waitNumService.execute(storeNum, session);
		return waitNum;
	}
	
	@PostMapping("setWaitNum")
	public String setWaitNum(Integer waitNum, BookCommand bookCommand) {
		// 대기번호 테이블에 값 넣기
		// bookStatus를 예약완료로 바꾸기
		return "redirect:/book/memberBookDetail?bookNum=" + bookCommand.getBookNum();
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
	public @ResponseBody void bookFinished(@RequestParam("bookNum") String bookNum, @RequestParam("finalPrice") String finalPrice
			, HttpSession session) {
		String bookStatus = "방문완료";
		bookMapper.bookStatusUpdate(bookNum, bookStatus);
		bookMapper.bookFinalPriceUpdate(bookNum, finalPrice);
		memberSavePointService.execute(bookNum, finalPrice, session);
	}
}
