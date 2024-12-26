package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.FriendMapper;

@Service
public class FriendDeleteService {
	@Autowired
	FriendMapper friendMapper;
	
	public void execute(String friendReqNum) {
		friendMapper.friendReqDelete(friendReqNum);
	}
}
