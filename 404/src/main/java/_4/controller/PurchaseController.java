package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.service.purchase.iniPayReqService;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	iniPayReqService iniPayReqService;
	
	@GetMapping("payment")
	public String payment(@RequestParam String purchaseNum, Model model) {
		
		iniPayReqService.execute(purchaseNum, model);
		return "thymeleaf/purchase/payment";
	}
	
	@PostMapping("INIstdpay_pc_return")
	public String iniReturn (){
		
		return "redirect:/";
	}
}
