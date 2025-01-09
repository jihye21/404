package _4.service.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.UserCommand;
import _4.domain.AuthDTO;
import _4.mapper.MemberMapper;
import _4.mapper.UserMapper;
import _4.service.member.MemberDeleteService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberDeleteCheckService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MemberDeleteService memberDeleteService;
	@Autowired
	UserMapper userMapper;
	
	public void execute(UserCommand userCommand, HttpSession session) {
		AuthDTO auth = userMapper.loginSelectOne(userCommand.getUserId());
		
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
			// 30일 미만일 경우
			session.setAttribute("auth", auth);
			auth.setLastLoginTime(LocalDateTime.now());
			userMapper.save(auth);
		}
	}
}
