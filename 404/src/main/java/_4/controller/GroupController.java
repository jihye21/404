package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.GroupCommand;
import _4.domain.GroupDTO;
import _4.service.group.GroupAlarmCount;
import _4.service.group.GroupAlarmListService;
import _4.service.group.GroupEnterService;
import _4.service.group.GroupListService;
import _4.service.group.GroupMemberSearchService;
import _4.service.group.GroupQuitService;
import _4.service.group.GroupRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("group")
public class GroupController {
	@Autowired
	GroupQuitService groupQuitService;
	@Autowired
	GroupEnterService groupEnterService;
	@Autowired
	GroupAlarmListService groupAlarmListService;
	@Autowired
	GroupAlarmCount groupAlarmCount;
	@Autowired
	GroupListService groupListService;
	@Autowired
	GroupRegistService groupRegistService;
	@Autowired
	GroupMemberSearchService groupMemberSearchService;
	@GetMapping("groupList")
	public String groupList(HttpSession session, Model model) {
		groupListService.execute(session, model);
		groupAlarmListService.execute(session, model);
		return "thymeleaf/group/groupList";
	}
	
	@PostMapping("GroupMemberSearch")
	public @ResponseBody List<GroupDTO> GroupMemberSearch(@RequestParam("memName")String memName
			, HttpSession session, Model model) {
		List<GroupDTO> groupList = groupMemberSearchService.execute(memName, session);
		
		return groupList;
	}
	
	@PostMapping("GroupForm")
	public String GroupForm(GroupCommand groupCommand, HttpSession session) {
		groupRegistService.execute(groupCommand, session);
		return "redirect:/group/groupList";
	}
	
	@PostMapping("alarmCount")
	public @ResponseBody Integer alarmCount(HttpSession session) {
		Integer alarmCount = groupAlarmCount.execute(session);
		
		return alarmCount;
	}
	
	@PostMapping("groupEnter")
	public @ResponseBody void groupEnter(@RequestParam ("groupNum") String groupNum, HttpSession session) {
		groupEnterService.execute(groupNum, session);
	}
	
	@PostMapping("groupQuit")
	public @ResponseBody void groupQuit(@RequestParam ("groupNum") String groupNum, HttpSession session) {
		groupQuitService.execute(groupNum, session);
	}
}
