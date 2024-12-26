package _4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("order")
public class OrderController {
	@PostMapping("themeOrder")
	public String themeOrder(String themeNum, String themeTime) {
		return "thymeleaf/order/themeOrderPage";
	}
}
