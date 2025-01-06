package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupEnterService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public void execute(String groupNum, HttpSession session) {
		GroupDTO groupDTO = new GroupDTO();
		
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		String memNum = userNumService.execute(session);
		groupDTO.setGroupNum(groupNum);
		groupDTO.setMemNum(memNum);
		
		groupMapper.groupAlarmEnter(groupDTO);
		groupMapper.groupMemberInsert(groupDTO);
	}
}
