package _4.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;

@Service
public class GroupPaymentHistoryListService {
	@Autowired
	GroupMapper groupMapper;
	public void execute(String groupNum, Model model) {
			
		List <GroupDTO> groupPaymentHistoryList 
		= groupMapper.groupPaymentHistorySelectAll(groupNum);

		model.addAttribute("groupPaymentHistoryList", groupPaymentHistoryList);

	}
}
