package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.service.employee.EmpPointRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("kafka")
public class KafkaController {
	@Autowired
	EmpPointRegistService empPointRegistService;
	@GetMapping("app")
	public String app(HttpSession session) {
		
		return "thymeleaf/kafka/App";
	}
	
	@PostMapping("pointRegist")
	public @ResponseBody boolean pointRegist(HttpSession session, @RequestParam ("empPoint") Integer empPoint) {
		
		Boolean point = empPointRegistService.execute(session, empPoint);
		return point;
	}
}
