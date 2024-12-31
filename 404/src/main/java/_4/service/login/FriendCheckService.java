package _4.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.LoginMapper;

@Service
public class FriendCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(String friendNickname) {
		String resultReqFriend = loginMapper.selectFriendReqCheck(friendNickname);
		return resultReqFriend;
		
		
		// String resultFriend = loginMapper.selectFriendListCheck(friendNickname); // 친구리스트 테이블에서 값을 확인 
		// return resultFriend;
	}
}
