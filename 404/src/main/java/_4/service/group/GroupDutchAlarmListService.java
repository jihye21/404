package _4.service.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupDutchAlarmListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	GroupMapper groupMapper;
	public void execute(HttpSession session, Model model) {
		String memNum = userNumService.execute(session);
		
		List<GroupDTO> groupDTO = groupMapper.groupDutchAlarmSelectAll(memNum);
		
		for(GroupDTO book : groupDTO) {
			String bookNum = book.getBookNum();
			
			BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
			
			if(bookDTO == null || 
				bookDTO.getBookStatus().equals("예약취소")){
				/*
				if(bookDTO.getBookStatus().equals("결제대기중")) {
					//"결제대기중"인 더치페이 요청은 삭제 }
				*/
				groupMapper.dutchAlarmDelete(bookNum);
				
			}
		}
		
		List<GroupDTO> groupDutchAlarmList = new ArrayList<>();
		groupDutchAlarmList = groupMapper.groupDutchAlarmSelectAll(memNum);
		
		model.addAttribute("groupDutchAlarmList", groupDutchAlarmList);
	}
}
