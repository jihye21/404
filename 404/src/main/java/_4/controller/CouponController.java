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

import _4.command.CouponCommand;
import _4.domain.MemberDTO;
import _4.service.coupon.CouponDeleteService;
import _4.service.coupon.CouponDetailService;
import _4.service.coupon.CouponListService;
import _4.service.coupon.CouponRegistService;
import _4.service.coupon.CouponUpdateService;
import _4.service.coupon.MemberSearchService;
import _4.service.coupon.MemberCouponRegistService;
import _4.service.coupon.MemberCouponSend;
import _4.service.coupon.memberCouponListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("coupon")
public class CouponController {
	@Autowired
	MemberCouponSend memberCouponSend;
	@Autowired
	MemberSearchService memberSearchService;
	@Autowired
	memberCouponListService memberCouponListService;
	@Autowired
	MemberCouponRegistService memberCouponRegistService;
	@Autowired
	CouponDeleteService couponDeleteService;
	@Autowired
	CouponUpdateService couponUpdateService;
	@Autowired
	CouponDetailService couponDetailService;
	@Autowired
	CouponListService couponListService;
	@Autowired
	CouponRegistService couponRegistService;
	
	@GetMapping("couponList")
	public String couponList(HttpSession session, Model model) {
		couponListService.execute(session, model);
		return "thymeleaf/coupon/couponList";
	}
	
	@PostMapping("couponList")
	public String couponList1(HttpSession session, Model model) {
		couponListService.execute(session, model);
		return "thymeleaf/coupon/couponList";
	}
	
	@GetMapping("couponForm")
	public String couponForm() {
		return "thymeleaf/coupon/couponForm";
	}
	
	@PostMapping("couponRegist")
	public String couponRegist(CouponCommand couponCommand, HttpSession session) {
		couponRegistService.execute(couponCommand, session);
		return "redirect:couponList";
	}
	
	@GetMapping("couponDetail")
	public String couponDetail(@RequestParam String couponNum, Model model) {
		couponDetailService.execute(couponNum, model);
		return "thymeleaf/coupon/couponDetail";
	}
	@PostMapping("couponModify")
	public String couponModify(@RequestParam String couponNum, Model model) {
		couponDetailService.execute(couponNum, model);
		return "thymeleaf/coupon/couponModify";
	}
	@PostMapping("couponUpdate")
	public String couponUpdate(@RequestParam String couponNum, CouponCommand couponCommand, HttpSession session) {
		couponUpdateService.execute(couponNum, couponCommand, session);
		return "redirect:couponDetail?couponNum="
				+ couponNum;
	}
	@GetMapping("couponDelete")
	public String couponDelete(@RequestParam String couponNum, HttpSession session) {
		couponDeleteService.execute(couponNum, session);
		return "redirect:couponList";
	}
	@GetMapping("memberCoupon")
	public String memberCoupon(HttpSession session, Model model) {
		memberCouponListService.execute(session, model);
		return "thymeleaf/coupon/memberCoupon";
	}
	@PostMapping("memberCouponRegist")
	public String memberCouponRegist(HttpSession session, CouponCommand couponCommand) {
		memberCouponRegistService.execute(session, couponCommand);
		return "redirect:memberCoupon";
	}
	
	//선물 보낼 회원 찾기
	@PostMapping("memberSearch")
	public @ResponseBody List<MemberDTO> memberSearch(@RequestParam ("memNickname") String memNickname
			, Model model) {
		List<MemberDTO> memberSearchList = memberSearchService.execute(memNickname, model);
		return memberSearchList;
	}
	
	//회원에게 쿠폰 코드를 선물보내기
	@PostMapping("memberCouponSend")
	public @ResponseBody void memberCouponSend(@RequestParam ("memNickname") String memNickname
			, @RequestParam ("couponCode") String couponCode) {
		memberCouponSend.execute(memNickname, couponCode);
	}
}
