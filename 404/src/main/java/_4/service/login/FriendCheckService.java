package _4.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.FriendMapper;
import _4.mapper.LoginMapper;

@Service
public class FriendCheckService {
	@Autowired
	FriendMapper friendMapper;
	
	public String execute(String friendNickname) {
		String resultReqFriend = friendMapper.selectFriendReqCheck(friendNickname);
		return resultReqFriend;
	}
}
