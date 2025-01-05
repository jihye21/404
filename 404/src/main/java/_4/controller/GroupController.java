package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.domain.GroupDTO;
import _4.group.GroupMemberSearchService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("group")
public class GroupController {
	@Autowired
	GroupMemberSearchService groupMemberSearchService;
	@GetMapping("groupList")
	public String groupList() {
		
		return "thymeleaf/group/groupList";
	}
	
	@PostMapping("GroupMemberSearch")
	public @ResponseBody List<GroupDTO> GroupMemberSearch(@RequestParam("memName")String memName
			, HttpSession session, Model model) {
		List<GroupDTO> groupList = groupMemberSearchService.execute(memName, session);
		
		return groupList;
	}
}
