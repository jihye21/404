package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.FriendAddRequestDTO;
import _4.domain.FriendDTO;
import _4.mapper.FriendMapper;
import _4.mapper.MainMapper;

@Service
public class FriendRegistService {		// 친구 요청을 등록
	@Autowired
	FriendMapper friendMapper;
	@Autowired
	MainMapper mainMapper;
	
	public void execute(String friendReqNum) {
		FriendAddRequestDTO friendAddRequestDTO = friendMapper.friendSelectOne(friendReqNum);
		FriendDTO friendDTO = new FriendDTO();
		String FriendNum = mainMapper.autoNumSelect("friendList", "friend_num", "friend_");
		friendDTO.setMemNum(friendAddRequestDTO.getToNum());
		friendDTO.setFriendNum(FriendNum);
		friendMapper.friendAddInsert(friendDTO);
	}
}
