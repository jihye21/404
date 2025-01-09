package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.BookCommand;
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
		
		//더치페이에서 자신을 제외
		List<String> dutchMember = bookCommand.getDutchMember();
		String mem = userNumService.execute(session);
		for(String memNum : dutchMember) {
			if(mem != memNum) {
				//멤버를 제외한 사람에게 더치페이 알림 전송하기
				groupMapper.groupDutchAlarmInsert(groupNum, dutchPrice, memNum, bookNum);
			}
		}
	}
}
