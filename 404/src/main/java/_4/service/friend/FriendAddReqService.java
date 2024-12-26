package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.FriendAddRequestCommand;
import _4.domain.FriendAddRequestDTO;
import _4.mapper.FriendMapper;
import _4.mapper.service.AutoNumService;

@Service
public class FriendAddReqService {		// 친구 추가 요청 서비스
	@Autowired
	FriendMapper friendMapper;
	@Autowired
	AutoNumService autoNumService;
	
	public void execute(FriendAddRequestCommand friendAddRequestCommand,String fromNum) {
		FriendAddRequestDTO friendAddRequestDTO = new FriendAddRequestDTO();
		String friendReqNum = autoNumService.execute("friendreqres", "friend_req_num", "friend_req_");
		friendAddRequestDTO.setFriendReqNum(friendReqNum);
		friendAddRequestDTO.setFromNum(fromNum);
		String toNum = friendMapper.changeNick(friendAddRequestCommand.getNickName());
		friendAddRequestDTO.setToNum(toNum);
		
		friendMapper.friendAddRequestInsert(friendAddRequestDTO);
	}
}
