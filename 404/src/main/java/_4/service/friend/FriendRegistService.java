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
		FriendAddRequestDTO friendAddRequestDTO = friendMapper.friendReqSelectOne(friendReqNum); //  친구 요청서에서 값을 받아옴
		FriendDTO friendDTO = new FriendDTO();
		friendDTO.setMemNum(friendAddRequestDTO.getToNum());
		friendDTO.setFriendNum(friendAddRequestDTO.getFromNum());
		String fromNick = friendMapper.changeNum(friendAddRequestDTO.getFromNum());						// 상대 친구리스트의 나의 정보를 번호에서 닉네임으로 치환
		friendDTO.setFriendNickname(fromNick);
		friendMapper.friendAddInsert(friendDTO);
		
		friendDTO.setMemNum(friendAddRequestDTO.getFromNum());			// 친구 승인 시 상대의 친구 상태도 변해야 하므로 상대의 친구리스트를 불러오는 것도 요청서의 상대의 번호를 불러와 삽입
		friendDTO.setFriendNum(friendAddRequestDTO.getToNum());			// 요청서의 나의 번호를 상대 친구리스트의 삽입
		friendDTO.setFriendNickname(friendAddRequestDTO.getMemNickname());
		friendMapper.friendAddInsert(friendDTO);
		
		
	}
}
