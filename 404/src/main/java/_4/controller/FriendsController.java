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

import _4.command.FriendAddRequestCommand;
import _4.command.FriendCommand;
import _4.domain.FriendAddRequestDTO;
import _4.domain.FriendDTO;
import _4.mapper.FriendMapper;
import _4.mapper.service.UserNumService;
import _4.service.friend.FriendAddReqService;
import _4.service.friend.FriendDeleteService;
import _4.service.friend.FriendListService;
import _4.service.friend.FriendRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("friends")
public class FriendsController {
	@Autowired
	UserNumService userNumService;
	@Autowired
	FriendAddReqService friendAddReqService;
	@Autowired
	FriendMapper friendMapper;
	
	
	@Autowired
	FriendListService friendListService;
	@RequestMapping("friendsList")
	public String friendsList(Model model, HttpSession session) {
		friendListService.execute(model, session);
		String memNum = userNumService.execute(session);
		List<FriendAddRequestDTO> friendAddReq = friendMapper.friendReqSelectAll(memNum);
		Integer friendAddCount = friendAddReq.size();
		model.addAttribute("friendAddCount", friendAddCount);
		return "thymeleaf/friend/friendsList";
	}
	
	@GetMapping("friendAdd")	// 친구 추가 요청(get)
	public String friendAdd(FriendCommand friendCommand, Model model, HttpSession session) {
		String nickname = userNumService.execute(session);
		model.addAttribute("nickname", nickname);
		return "thymeleaf/friend/friendAddFrom";
	}
	
	@PostMapping("friendAddReq")	// 친구 추가 요청(post) 후 확인 페이지로
	public String friendReq(FriendAddRequestCommand friendAddRequestCommand, HttpSession session) {
		String fromNum = userNumService.execute(session);
		friendAddReqService.execute(friendAddRequestCommand, fromNum);
		return "thymeleaf/friend/sendOk";
	}
	
	@GetMapping("friendAccList")		// 친구 요청을 리스트에 출력
	public String friendAccList(Model model, HttpSession session) {
		String toNum = userNumService.execute(session);
		List<FriendAddRequestDTO> list = friendMapper.friendReqSelectAll(toNum);
		model.addAttribute("list", list);
		return "thymeleaf/friend/accList";
	}
	
	@GetMapping("friendReqDetail")
	public String friendReqDetail(@RequestParam("friendReqNum") String friendReqNum, Model model, HttpSession session) {
		FriendAddRequestDTO dto = friendMapper.friendReqSelectOne(friendReqNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/friend/friendReqDetail";
	}
	
	@Autowired
	FriendRegistService friendRegistService;
	@Autowired
	FriendDeleteService friendDeleteService;
	@PostMapping("friendReqOk")
	public @ResponseBody void friendReqOk(@RequestParam("friendReqNum") String friendReqNum) {
		friendRegistService.execute(friendReqNum);
		friendDeleteService.execute(friendReqNum);
	}
	
	@PostMapping("friendReqNo")
	public void friendReqNo(@RequestParam("friendReqNum") String friendReqNum) {
		friendDeleteService.execute(friendReqNum);
	}
	
	@GetMapping("friendDetail")
	public String friendDetail() {
		return "thymeleaf/friend/friendDetail";
	}
}
