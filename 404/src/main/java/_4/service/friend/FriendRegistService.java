package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.FriendAddRequestDTO;
import _4.mapper.FriendAddReqMapper;
import _4.mapper.MainMapper;

@Service
public class FriendRegistService {
	@Autowired
	FriendAddReqMapper friendAddReqMapper;
	@Autowired
	MainMapper mainMapper;
	
	public void execute(String friendReqNum) {
		FriendAddRequestDTO friendAddRequestDTO = friendAddReqMapper.friendSelectOne(friendReqNum);
		
	}
}
