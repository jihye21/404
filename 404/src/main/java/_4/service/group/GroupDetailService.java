package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;

@Service
public class GroupDetailService {
	@Autowired
	GroupMapper groupMapper;
	public void execute(String groupNum, Model model) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupNum(groupNum);
		
		List<GroupDTO> groupDetailList = groupMapper.groupMemberSelectAll(groupDTO);
		
		String groupName = groupMapper.groupNameSelect(groupDTO);
		
		model.addAttribute("groupName", groupName);
		model.addAttribute("groupDetailList", groupDetailList);
	}
	
}
