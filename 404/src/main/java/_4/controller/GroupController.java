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
import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.domain.ReviewDTO;
import _4.mapper.BookMapper;
import _4.mapper.ReviewMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.group.GroupAlarmCount;
import _4.service.group.GroupAlarmListService;
import _4.service.group.GroupDetailService;
import _4.service.group.GroupDutchAlarmService;
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
	ReviewMapper reviewMapper;
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	GroupDetailService groupDetailService;
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
	
	@GetMapping("groupDetail")
	public String groupDetail(@RequestParam String groupNum, Model model, HttpSession session) {
		//그룹 회원 목록 가져오기
		groupDetailService.execute(groupNum, model);
		
		//예약 리스트 가져오기
		String memberNum = userNumService.execute(session);
		List<BookDTO> list = bookMapper.bookGroupSelectAllWithMember(memberNum, groupNum);
		model.addAttribute("list", list);
		
		return "thymeleaf/group/groupDetail";
	}
	
	@GetMapping("groupBookDetail")
	public String groupBookDetail(String bookNum, Model model) {
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		ReviewDTO reviewDTO = reviewMapper.reviewSelectOneWithBookNum(bookNum);
		model.addAttribute("dto", dto);
		model.addAttribute("reviewDTO", reviewDTO);
		
		return "thymeleaf/group/groupBookDetail";
	}
	
	/*
	@PostMapping("groupDutchAlarm")
	public @ResponseBody void groupDutchAlarm(@RequestParam ("dutchMember")String dutchMember
			, @RequestParam ("groupNum") String groupNum
			, @RequestParam ("dutchPrice") String dutchPrice) {
		groupDutchAlarmService.execute(dutchMember, groupNum, dutchPrice);
	}
	*/
}
