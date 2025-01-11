package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.BookCommand;
import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupDutchAlarmService {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(String bookNum, BookCommand bookCommand
			, HttpSession session) {
		String groupNum = bookCommand.getGroupNum();
		Integer dutchPrice = bookCommand.getDutchPrice();
		
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupNum(groupNum);
		//더치페이에서 자신을 제외
		List<GroupDTO> dutchMember = groupMapper.groupMemberSelectAll(groupDTO);
		String mem = userNumService.execute(session);
		for(GroupDTO group : dutchMember) {
			String memNum = group.getMemNum();
			//if(mem != memNum) { 리더를 포함할지 제외할지 고민..
				//멤버를 제외한 사람에게 더치페이 알림 전송하기
				groupMapper.groupDutchAlarmInsert(groupNum, dutchPrice, memNum, bookNum);
			//}
		}
	}
}
