package _4.service.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
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
	public AuthDTO execute(UserCommand userCommand, BindingResult result, HttpSession session) {
		AuthDTO auth = userMapper.login(userCommand.getUserId());
		if(auth != null) {
			if(auth.getUserPw() != userCommand.getUserPw()) {
				// 로그인 성공
				session.setAttribute("auth", auth);
				auth.setLastLoginTime(LocalDateTime.now());
				userMapper.save(auth);
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
		
		/*
		Date lastLoginGetDate = memberMapper.lastLoginGetDate(auth); // 사용자의 마지막 로그인 시간
		Date currentDate = new Date(); // 현재 날짜

		// 두 날짜 간의 차이를 밀리초로 계산
		long differenceInMillis = currentDate.getTime() - lastLoginGetDate.getTime();

		// 밀리초를 일수로 변환 (1일 = 24시간 * 60분 * 60초 * 1000밀리초)
		long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

		// 30일 이상 경과했는지 확인
		if (differenceInDays >= 30) {
			// 30일 이상 경과한 경우 실행할 로직
			System.out.println("30일 이상 경과했습니다.");
			memberDeleteService.execute(auth);
		} else {
			System.out.println("30일 미만 경과했습니다.");
			
		}*/
		
	}
	
	
}
