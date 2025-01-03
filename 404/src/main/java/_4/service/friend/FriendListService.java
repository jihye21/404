package _4.service.friend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.FriendDTO;
import _4.mapper.FriendMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class FriendListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	FriendMapper friendMapper;
	
	public void execute(Model model, HttpSession session) {
		String memNick = userNumService.execute(session);
		List<FriendDTO> list = friendMapper.friendsSelectAll(memNick);
		model.addAttribute("list", list);
	}
}
