package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.GroupShareDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupChattingInfoService {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	UserNumService userNumService;
	
	public GroupShareDTO execte(String groupNum, HttpSession session){
		GroupShareDTO groupShareDTO = new GroupShareDTO();
		
		groupShareDTO.setMemNum(userNumService.execute(session));
		groupShareDTO.setGroupNum(groupNum);
		
		groupShareDTO = groupMapper.groupMemberSelectOne(groupShareDTO);
		return groupShareDTO;
	}
}
