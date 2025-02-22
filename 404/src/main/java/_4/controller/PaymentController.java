package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.mapper.PurchaseMapper;
import _4.service.group.GroupDutchService;
import _4.service.payment.INIstdpayPcReturn;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	GroupDutchService groupDutchService;
	@Autowired
	INIstdpayPcReturn iniPayReturnService;
	@Autowired
	PurchaseMapper purchaseMapper;
	
	
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn (HttpServletRequest request, HttpSession session) {
		iniPayReturnService.execute(request, session);
		return "redirect:/";
	}
	
	@RequestMapping("close")
	public String close() {
		
		return "thymeleaf/purchase/close";
	}
}
