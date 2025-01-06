package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupAlarmCount {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public Integer execute(HttpSession session) {
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		
		String memNum = userNumService.execute(session);
		
		Integer alarmCount = groupMapper.groupAlarmCount(memNum);
		
		return alarmCount;
	}
}
