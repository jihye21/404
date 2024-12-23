package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.UserCommand;
import _4.domain.AuthDTO;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.UserNumService;
import _4.service.owner.OwnerLoginService;
import _4.service.store.StoreListService;
import _4.service.user.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	StoreListService storeListService;//storeList
	@Autowired
	LoginService loginService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	OwnerLoginService ownerLoginService;
	
	@Autowired
	MemberMapper memberMapper;
	
	@GetMapping("loginForm")
	public String loginForm(Model model, UserCommand userCommand) {
		model.addAttribute("userCommand", userCommand);
		return "thymeleaf/user/loginForm";
	}
	
	@PostMapping("login")
	public String login(@Validated UserCommand userCommand, BindingResult result, HttpSession session, Model model) {
		AuthDTO auth = loginService.execute(userCommand, result, session);
		if(result.hasErrors()) {
			return "thymeleaf/user/loginForm";
		}
		if(auth.getGrade().equals("member")) {
			String memberNum = userNumService.execute(session);
			MemberDTO dto = memberMapper.memberSelectOne(memberNum);
			model.addAttribute("dto", dto);
			storeListService.execute(model);//storeList
			return "thymeleaf/index";
		}
		else if(auth.getGrade().equals("employee")) return "redirect:/employee/employeeMainPage";
		else if(auth.getGrade().equals("owner")) {
			String ownerNum = userNumService.execute(session);
			String link = ownerLoginService.execute(ownerNum);
			return link;
		}
		else return "thymeleaf/user/loginForm";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
