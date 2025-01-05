package _4.group;

import java.util.ArrayList;
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
public class GroupMemberSearchService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public List<GroupDTO> execute(String memName, HttpSession session) {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		
		//member인지 확인
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")){
			
			String memNum = userNumService.execute(session);
			
			groupList = groupMapper.groupMemberSearch(memName, memNum);
		}
		
		 
		return groupList;
	}
}
