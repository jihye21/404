package _4.service.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public void execute(HttpSession session, Model model) {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		List<GroupDTO> groupMemberList = new ArrayList<GroupDTO>();
		List<GroupDTO> allGroupMemberList = new ArrayList<GroupDTO>();
		
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		String memNum = userNumService.execute(session);
		groupList = groupMapper.groupSelectAll(memNum);
		
		//그룹 번호와 그룹 이름을 가져온다.
		model.addAttribute("myGroupList", groupList);
		
		for( GroupDTO groupNumList : groupList) {
			groupMemberList = groupMapper.groupMemberSelectAll(groupNumList);
			allGroupMemberList.addAll(groupMemberList);
		}
		groupMemberList = allGroupMemberList;
		
		model.addAttribute("groupMemberList", groupMemberList);
		model.addAttribute("memNum", memNum);
	}
}
