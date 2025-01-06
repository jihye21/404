package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.GroupCommand;
import _4.domain.AuthDTO;
import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupRegistService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GroupMapper groupMapper;
	public void execute(GroupCommand groupCommand, HttpSession session) {
		GroupDTO groupDTO = new GroupDTO();
		
		//그룹 번호
		String groupNum = autoNumService.execute("group_list", "group_num", "group_");
		groupDTO.setGroupNum(groupNum);
		groupDTO.setGroupName(groupCommand.getGroupName());
		//리더 번호
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		String memNum = userNumService.execute(session);
		groupDTO.setMemNum(memNum);
		groupMapper.groupInsert(groupDTO);
		groupMapper.groupMemberInsert(groupDTO);
		
		String[] groupList = groupCommand.getMemNum().split(",");
		for(String num : groupList) {
			groupDTO.setMemNum(num);
			groupMapper.groupMemberAlarmInsert(groupDTO);
		}
		
	}
}
