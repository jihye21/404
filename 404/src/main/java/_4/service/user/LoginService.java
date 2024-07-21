package _4.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import _4.command.UserCommand;
import _4.domain.AuthDTO;
import _4.mapper.MemberMapper;
import _4.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MemberDeleteCheckService memberDeleteCheckService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public AuthDTO execute(UserCommand userCommand, BindingResult result, HttpSession session) {
		AuthDTO auth = userMapper.login(userCommand.getUserId());
		if(auth != null) {
			if(passwordEncoder.matches(userCommand.getUserPw(), auth.getUserPw())) {
				if(auth.getGrade().equals("member")) memberDeleteCheckService.execute(userCommand, session);
				else session.setAttribute("auth", auth);
			}
			else {
				// 비밀번호가 일치하지 않습니다.
				result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 존재하지 않습니다.");
			}
		}
		else {
			// 아이디가 존재하지 않습니다.
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
		}
		return auth;
		
	}
	
}
/*
if(auth.getUserEmailCheck() == null) {
	// 회원에는 있지만 이메일 인증이 안되었을때
	result.rejectValue("userId", "loginCommand.userId", "이메일 인증이 안되었습니다.");
}
else {
	if(auth.getGrade().equals("member")) {
		memberDeleteCheckService.execute(userCommand, session);
	}
	else {
		session.setAttribute("auth", auth);
	}

}

auth.getUserPw() == userCommand.getUserPw(
*/


