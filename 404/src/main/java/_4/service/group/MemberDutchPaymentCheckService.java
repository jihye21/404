package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberDutchPaymentCheckService {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(String groupNum, HttpSession session, Model model) {
		String memNum = userNumService.execute(session);
		
		List<GroupDTO> memberPay = groupMapper.memberDutchPaymentCheckSelectOne(memNum, groupNum);
	
		model.addAttribute("memberPay", memberPay);
	}
}
