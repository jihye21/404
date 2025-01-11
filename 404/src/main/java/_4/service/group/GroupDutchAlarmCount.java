package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupDutchAlarmCount {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	UserNumService userNumService;
	public Integer execute(HttpSession session) {
		String memNum = userNumService.execute(session);
		Integer payAlarmCount = groupMapper.groupDutchAlarmCount(memNum);
		return payAlarmCount;
	}
}
