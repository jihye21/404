package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.FriendMapper;

@Service
public class MemberCheckService {
	@Autowired
	FriendMapper friendMapper;
	
	public String execute(String friendNickname) {
		String resultMemberChk = friendMapper.changeNick(friendNickname);
		return resultMemberChk;
	}
}
