package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.service.payment.INIstdpayPcReturn;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	INIstdpayPcReturn iniPayReturnService;
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn (HttpServletRequest request) {
		iniPayReturnService.execute(request);
		return "redirect:/";
	}
	
	@RequestMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
}
