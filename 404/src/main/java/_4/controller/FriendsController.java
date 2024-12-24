package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.command.FriendAddRequestCommand;
import _4.domain.FriendAddRequestDTO;
import _4.mapper.FriendAddReqMapper;
import _4.mapper.service.UserNumService;
import _4.service.friend.FriendAddReqService;
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
	FriendAddReqMapper friendAddReqMapper;
	
	@RequestMapping("friendsList")
	public String friendsList() {
		return "thymeleaf/friend/friendsList";
	}
	
	@GetMapping("friendAdd")	// 친구 추가 요청(get)
	public String friendAdd() {
		return "thymeleaf/friend/friendAddFrom";
	}
	
	@PostMapping("friendAddReq")	// 친구 추가 요청(post) 후 확인 페이지로
	public String friendReq(FriendAddRequestCommand friendAddRequestCommand, HttpSession session) {
		String memNum = userNumService.execute(session);
		friendAddReqService.execute(friendAddRequestCommand, session, memNum);
		return "thymeleaf/friend/sendOk";
	}
	
	@GetMapping("friendAccList")
	public String friendAccList() {
		return "thymeleaf/friend/accList";
	}
	
	@GetMapping("friendReqDetail")
	public String friendReqDetail(@RequestParam("friendReqNum") String friendReqNum, Model model) {
		FriendAddRequestDTO dto = friendAddReqMapper.friendSelectOne(friendReqNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/friend/reqDetail";
	}
	
	@Autowired
	FriendRegistService friendRegistService;
	@PostMapping("friendReqOk")
	public void friendReqOk(@RequestParam("friendReqNum") String friendReqNum) {
		friendRegistService.execute(friendReqNum);
		//friendDeleteService.execute(friendReqNum);
	}
	
	@PostMapping("friendReqNo")
	public void friendReqNo(@RequestParam("friendReqNum") String friendReqNum) {
		//friendDeleteService.execute(friendReqNum);
	}
}
