package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.service.login.IdCheckService;
import _4.service.login.NickNameCheckService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	NickNameCheckService nickNameCheckService;
	@Autowired
	IdCheckService idCheckService;
	
	@PostMapping("userNickNameCheck")
	public @ResponseBody String userNickNameCheck(
			@RequestParam(value="userNick") String memNickName) {
		String resultNick = nickNameCheckService.execute(memNickName);
		if(resultNick == null) {
			return "사용가능한 닉네임입니다.";
		}else {
			return "이미 존재하는 닉네임입니다.";
		}
	}
	
	@PostMapping("userIdCheck")
	public @ResponseBody String userIdCheck(
			@RequestParam(value="userId") String userId) {
		String resultId = idCheckService.execute(userId);
		if(resultId == null) {
			return "사용가능한 아이디입니다.";
		}else {
			return "이미 존재하는 아이디입니다.";
		}
	}
}