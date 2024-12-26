package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.service.user.CouponRegistService;

@Controller
@RequestMapping("coupon")
public class CouponController {
	@Autowired
	CouponRegistService couponRegistService;
	@GetMapping("couponForm")
	public String couponForm() {
		return "thymeleaf/coupon/couponForm";
	}
	
	@PostMapping("couponRegist")
	public String couponRegist() {
		couponRegistService.execute();
		//어디로 보내지...
		return "redirect:/";
	}
}
