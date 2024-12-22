package _4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Controller
@SpringBootApplication
public class Application {
	@Autowired
	UserNumService userNumService;
	@Autowired
	MemberMapper memberMapper;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		return "thymeleaf/index";
	}
	
}
