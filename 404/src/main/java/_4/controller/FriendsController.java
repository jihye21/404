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
import _4.mapper.LoginMapper;
import _4.mapper.service.UserNumService;
import _4.service.friend.FriendAddReqService;
import _4.service.friend.FriendDeleteService;
import _4.service.friend.FriendDetailService;
import _4.service.friend.FriendListService;
import _4.service.friend.FriendRegistService;
import _4.service.friend.FriendReqDeleteService;
import _4.service.friend.MemberCheckService;
import _4.service.login.FriendCheckService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("friends")
public class FriendsController {
	@Autowired
	UserNumService userNumService;
	@Autowired
	FriendAddReqService friendAddReqService;
	@Autowired
	FriendReqDeleteService friendReqDeleteService;
	@Autowired
	FriendRegistService friendRegistService;
	@Autowired
	FriendDeleteService friendDeleteService;
	@Autowired
	FriendListService friendListService;
	@Autowired
	FriendCheckService friendCheckService;
	@Autowired
	MemberCheckService memberCheckService;
	@Autowired
	FriendDetailService friendDetailService;
	
	@Autowired
	FriendMapper friendMapper;
	@Autowired
	LoginMapper loginMapper;
	
	@PostMapping("friendNickCheck") // 친구 추가 중복 시스템
	public @ResponseBody String friendNickCheck(
			@RequestParam (value="userFriend") String friendNickname, HttpSession session) {
		String memberNum = userNumService.execute(session);
		String memberNick = friendMapper.changeNick(memberNum);

		if (friendNickname.equals(memberNick)) {
	        return "자신에게 친구 추가를 할 수 없습니다."; // 자기 자신을 친구 추가할 수 없다는 메시지 반환
	    }
		
		String friendNum = memberCheckService.execute(friendNickname);
		if(friendNum != null) {
			String resultFriendReq = friendCheckService.execute(friendNickname);
			if(resultFriendReq != null) {
				return "이미 요청한 상태입니다.";
			}
			else {
				String resultFriendList = friendMapper.selectFriendListCheck(friendNickname);
				if(resultFriendList != null) {
					return "이미 존재하는 친구입니다.";
				}
				else {
					return "친구 가능한 닉네임입니다.";
				}
			}
		}
		else {
			return "존재하지 않는 유저입니다.";
		}
		
	}
	
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
		String memberNum = userNumService.execute(session);
		String nickname = friendMapper.changeNick(memberNum);
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
	
	
	@PostMapping("friendReqOk")
	public @ResponseBody void friendReqOk(@RequestParam("friendReqNum") String friendReqNum) {
		friendRegistService.execute(friendReqNum);
		friendReqDeleteService.execute(friendReqNum);
	}
	
	@PostMapping("friendReqNo")
	public void friendReqNo(@RequestParam("friendReqNum") String friendReqNum) {
		friendReqDeleteService.execute(friendReqNum);
	}
	
	@GetMapping("friendDetail")
	public String friendDetail(@RequestParam("friendNum") String friendNum, Model model) {
		friendDetailService.execute(friendNum, model);
		return "thymeleaf/friend/friendDetail2";
	}
	
	@GetMapping("friendDelete")
	public String friendDelete(String friendNum, HttpSession session) {
		friendDeleteService.execute(session, friendNum);
		return "redirect:/friends/friendsList";
	}
}
