package _4.service.user;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.MemberCommand;
import _4.command.UserCommand;
import _4.domain.AuthDTO;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.UserMapper;
import _4.service.member.MemberDeleteService;
import jakarta.servlet.http.HttpSession;

@Service
public class DormancyInsertService {
	@Autowired
	MemberDeleteService memberDeleteService;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	LoginService loginService;

	public void execute(String memNum, HttpSession session, UserCommand userCommand) {
		memberMapper.memberStatusUpadate(memNum); // 상태를 null - status is null
		session.invalidate();
		// 1. 탈퇴시 상태 null
		// 2. 마지막 로그인 시간 호출
		// 3. 30일 이상이 지나면 삭제
		// 4. 30일 이내 로그인 하면 활성화
		
		
	}
}
