package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.FriendMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class FriendDeleteService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	FriendMapper friendMapper;
	public void execute(HttpSession session, String friendNum) {
		String memberNum = userNumService.execute(session);
		friendMapper.friendDelete(memberNum, friendNum);
	}
}
