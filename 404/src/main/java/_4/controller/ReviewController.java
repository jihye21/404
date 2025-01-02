package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.command.MemberCommand;
import _4.command.ReviewCommand;
import _4.service.review.ReviewDeleteService;
import _4.service.review.ReviewDetailService;
import _4.service.review.ReviewListService;
import _4.service.review.ReviewUpdateService;
import _4.service.review.ReviewWriteService;

@Controller
@RequestMapping("review")
public class ReviewController {
	
	@Autowired
	ReviewListService reviewListService;
	@RequestMapping("reviewList")
	public String reviewList(Model model) {
		reviewListService.execute(model);
		return "thymeleaf/review/reviewList";
	}
	
	@GetMapping("reviewWrite")
	public String reviewWrite() {
		return "thymeleaf/review/reviewWrite";
	}
	
	@Autowired
	ReviewWriteService reviewWriteService;
	@PostMapping("reviewWrite")
	public String reviewWrite(ReviewCommand reviewCommand) {
		reviewWriteService.execute(reviewCommand);
		return "redirect:reviewList";
	}
	
	@Autowired
	ReviewDetailService reviewDetailService;
	@GetMapping("reviewDetail")
	public String reviewDetail(@RequestParam("reviewNum") String reviewNum, Model model) {
		reviewDetailService.execute(reviewNum, model);
		return "thymeleaf/review/reviewDetail";
	}
	
	@GetMapping("reviewModify")
	public String reviewModify(@RequestParam("reviewNum") String reviewNum, Model model) {
		reviewDetailService.execute(reviewNum, model);
		return "thymeleaf/review/reviewModify";
	}
	
	@Autowired
	ReviewUpdateService reviewUpdateService;
	@PostMapping("reviewUpdate")
	public String reviewUpdate(ReviewCommand reviewCommand) {
		reviewUpdateService.execute(reviewCommand);
		return "redirect:reviewDetail?reviewNum=" + reviewCommand.getReviewNum();
	}
	
	@Autowired
	ReviewDeleteService reviewDeleteService;
	@GetMapping("reviewDelete")
	public String reviewDelete(String reviewNum) {
		reviewDeleteService.execute(reviewNum);
		return "redirect:reviewList";
	}
	
}
