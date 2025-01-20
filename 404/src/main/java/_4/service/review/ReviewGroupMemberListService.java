package _4.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class ReviewGroupMemberListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	BookMapper bookMapper;
	public void execute(String bookNum, Model model, HttpSession session) {
		//사용자 번호 보내기
		String memNum = userNumService.execute(session);
		model.addAttribute("memNum", memNum);
		
		//그룹 멤버 목록 보내기
		BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupNum(bookDTO.getGroupNum());
		List<GroupDTO> groupMemberList = groupMapper.groupMemberSelectAll(groupDTO);
		model.addAttribute("groupMemberList", groupMemberList);
	}
}	
