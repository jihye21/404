package _4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("kafka")
public class KafkaController {
	@GetMapping("app")
	public String app() {
		return "thymeleaf/kafka/App";
	}
}
