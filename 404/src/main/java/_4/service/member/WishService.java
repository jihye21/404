package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.MemberMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class WishService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	UserNumService userNumService;
	
	public void execute(String storeNum, HttpSession session) {
		String userNum = userNumService.execute(session);
		
		memberMapper.wishCheck(userNum, storeNum);
		
	}
}
