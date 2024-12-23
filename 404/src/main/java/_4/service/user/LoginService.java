package _4.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import _4.command.UserCommand;
import _4.domain.AuthDTO;
import _4.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	UserMapper userMapper;	
	public AuthDTO execute(UserCommand userCommand, BindingResult result, HttpSession session) {
		AuthDTO auth = userMapper.login(userCommand.getUserId());
		if(auth != null) {
			if(auth.getUserPw() != userCommand.getUserPw()) {
				// 로그인 성공
				session.setAttribute("auth", auth);
				System.out.println("세션 정보:" + auth);
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
