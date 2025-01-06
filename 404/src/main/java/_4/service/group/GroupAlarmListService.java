package _4.service.group;

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
public class GroupAlarmListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public void execute(HttpSession session, Model model) {
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		String memNum = userNumService.execute(session);
		
		List<GroupDTO> groupAlarmList = groupMapper.groupAlarmSelectAll(memNum);

		model.addAttribute("groupAlarmList", groupAlarmList);
	}
}
