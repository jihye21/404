package _4.service.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.FriendDTO;
import _4.mapper.FriendMapper;

@Service
public class FriendDetailService {
	@Autowired
	FriendMapper friendMapper;
	
	public void execute(String friendNum, Model model) {
		FriendDTO dto = friendMapper.friendSelectOne(friendNum);
		model.addAttribute("dto", dto);
	}
}
