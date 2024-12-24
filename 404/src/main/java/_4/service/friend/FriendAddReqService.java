package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.FriendAddRequestCommand;
import _4.domain.FriendAddRequestDTO;
import _4.mapper.FriendAddReqMapper;
import _4.mapper.service.AutoNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class FriendAddReqService {
	@Autowired
	FriendAddReqMapper friendAddReqMapper;
	@Autowired
	AutoNumService autoNumService;
	
	public void execute(FriendAddRequestCommand friendAddRequestCommand, HttpSession session, String memNum) {
		FriendAddRequestDTO friendAddRequestDTO = new FriendAddRequestDTO();
		String friendReqNum = autoNumService.execute("friend", "friend_req_num", "friend_req_");
		friendAddRequestDTO.setFriendReqNum(friendReqNum);
		friendAddRequestDTO.setMemNum(memNum);
		friendAddRequestDTO.setFriendRegistDate(friendAddRequestCommand.getFriendRegistDate());
		
		friendAddReqMapper.friendAddRequestInsert(friendAddRequestDTO);
	}
}
