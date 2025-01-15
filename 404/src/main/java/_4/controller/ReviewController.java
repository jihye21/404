package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.ReviewCommand;
import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.domain.ReviewDTO;
import _4.mapper.BookMapper;
import _4.mapper.ReviewMapper;
import _4.mapper.service.UserNumService;
import _4.service.group.GroupCheckService;
import _4.service.group.GroupMemberReviewCheckService;
import _4.service.member.MemberReviewSavePointService;
import _4.service.member.MemberSavePointService;
import _4.service.review.ReviewAnswerDeleteService;
import _4.service.review.ReviewAnswerModifyService;
import _4.service.review.ReviewAnswerService;
import _4.service.review.ReviewDeleteService;
import _4.service.review.ReviewDetailService;
import _4.service.review.ReviewListService;
import _4.service.review.ReviewUpdateService;
import _4.service.review.ReviewWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	MemberReviewSavePointService memberReviewSavePointService;
	@Autowired
	MemberSavePointService memberSavePointService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMemberReviewCheckService groupMemberReviewCheckService;
	@Autowired
	GroupCheckService groupCheckService;
	@Autowired
	ReviewWriteService reviewWriteService;
	@Autowired
	ReviewListService reviewListService;
	@Autowired
	ReviewDetailService reviewDetailService;
	@Autowired
	ReviewDeleteService reviewDeleteService;
	@Autowired
	ReviewAnswerService reviewAnswerService;
	@Autowired
	ReviewAnswerModifyService reviewAnswerModifyService;
	@Autowired
	ReviewAnswerDeleteService reviewAnswerDeleteService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	ReviewMapper reviewMapper;
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@RequestMapping("reviewList")
	public String reviewList(Model model) {
		reviewListService.execute(model);
		return "thymeleaf/review/reviewList";
	}
	
	@PostMapping("reviewForm")
	public String reviewWrite(@RequestParam("bookNum") String bookNum, Model model
			, HttpSession session) {
		String memNum = userNumService.execute(session);
		
		//그룹 결제인지 확인하기
		String groupCheck = groupCheckService.execute(bookNum);
		if(groupCheck != null) {
			BookDTO bookDTO = groupMemberReviewCheckService.execute(bookNum, session);
			if(bookDTO != null) {
				BookDTO dto = bookMapper.bookSelectOne(bookNum);
				model.addAttribute("dto", dto);
				return "thymeleaf/review/reviewWrite";
			}else {
				return "403";
			}
		}else {
			//1인 결제인지 확인
			BookDTO bookDTO = bookMapper.memberBookCheck(bookNum, memNum);
			if(bookDTO != null) {
				BookDTO dto = bookMapper.bookSelectOne(bookNum);
				model.addAttribute("dto", dto);
				return "thymeleaf/review/reviewWrite";
			}else {
				return "403";
			}
			
		}
		
	}
	
	
	@PostMapping("reviewWrite")
	public String reviewWrite(ReviewCommand reviewCommand, HttpSession session) {
		reviewWriteService.execute(reviewCommand, session);
		memberReviewSavePointService.execute(session);
		return "redirect:/book/memberBookDetail?bookNum=" + reviewCommand.getBookNum();
	}
	
	///@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("reviewDetail")
	public String reviewDetail(@RequestParam("reviewNum") String reviewNum, Model model) {
		reviewDetailService.execute(reviewNum, model);
		return "thymeleaf/review/reviewDetail";
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("reviewModify")
	public String reviewModify(@RequestParam("reviewNum") String reviewNum, Model model) {
		reviewDetailService.execute(reviewNum, model);
		return "thymeleaf/review/reviewModify";
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Autowired
	ReviewUpdateService reviewUpdateService;
	@PostMapping("reviewUpdate")
	public String reviewUpdate(ReviewCommand reviewCommand) {
		reviewUpdateService.execute(reviewCommand);
		return "redirect:reviewDetail?reviewNum=" + reviewCommand.getReviewNum();
	}
	
	@PostMapping("reviewDelete")
	public String reviewDelete(@RequestParam("reviewNum") String reviewNum) {
		ReviewDTO dto = reviewMapper.reviewSelectOne(reviewNum);
		reviewDeleteService.execute(reviewNum);
		System.out.println(dto.getBookNum());
		return "redirect:/book/memberBookDetail?bookNum=" + dto.getBookNum();
	}
	
	@PostMapping("reviewAnswerForm")
	public String reviewAnswerForm(@RequestParam("reviewNum") String reviewNum) {
		
		return "thymeleaf/review/reviewAnswerForm";
	}
	
	@PostMapping("reviewAnswer")
	public @ResponseBody void reviewAnswer(@RequestBody @RequestParam("reviewNum") String reviewNum, @RequestParam("reviewAnswerContents") String reviewAnswerContents) {
		reviewAnswerService.execute(reviewNum, reviewAnswerContents);
	}
	
	@PostMapping("reviewAnswerModify")
	public @ResponseBody void reviewAnswerModify(@RequestBody @RequestParam("reviewNum") String reviewNum, @RequestParam("reviewAnswerContents") String reviewAnswerContents) {
		reviewAnswerModifyService.execute(reviewNum, reviewAnswerContents);
	}
	
	@PostMapping("reviewAnswerDelete")
	public @ResponseBody void reviewAnswerModify(@RequestBody @RequestParam("reviewNum") String reviewNum) {
		reviewAnswerDeleteService.execute(reviewNum);
	}
	
}
