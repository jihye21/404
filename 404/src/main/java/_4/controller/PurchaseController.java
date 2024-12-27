package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.service.purchase.IniPayReqService;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	IniPayReqService iniPayReqService;
	
	@PostMapping("INIstdpay_pc_return")
	public String iniReturn (){
		
		return "redirect:/";
	}
}
