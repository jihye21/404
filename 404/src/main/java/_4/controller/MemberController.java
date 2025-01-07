package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.MemberCommand;
import _4.mapper.service.UserNumService;
import _4.service.member.MemberDetailService;
import _4.service.member.MemberPwUpdateService;
import _4.service.member.MemberRegistService;
import _4.service.member.MemberUpdateService;
import _4.service.member.MyPassConfirmService;
import _4.service.member.WishListService;
import _4.service.member.WishService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	WishService wishService;
	@Autowired
	WishListService wishListService;
	@Autowired
	MemberRegistService memberRegistService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	MemberPwUpdateService memberPwUpdateService;
	@Autowired
	MyPassConfirmService myPassConfirmService;
	
	@GetMapping("memberMainPage")
	public String memberMainPage(HttpSession session, Model model) {
		String memNum = userNumService.execute(session);
		model.addAttribute("memNum", memNum);
        return "thymeleaf/member/memberMainPage";
	}
	
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String memberRegist(Model model) {
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist(@Validated MemberCommand memberCommand, Model model) {
		memberRegistService.execute(memberCommand, model);
		 return "thymeleaf/member/welcome";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(String memNum, Model model, MemberCommand memberCommand) {
		memberDetailService.execute(memNum, model, memberCommand);
		return "thymeleaf/member/memberInfo";	// 내 정보 보기
	}
	
	@GetMapping("memberModify")
	public String memberModify(MemberCommand memberCommand, Model model) {
		return "thymeleaf/member/memberModify";		// 내 정보 수정
	}
	
	@PostMapping("memberModify")
	public String memberUpdate(MemberCommand memberCommand, Model model) {
		memberUpdateService.execute(memberCommand, model);
		return "redirect:memberDetail?memNum=" + memberCommand.getMemNum();
	}
	
	@RequestMapping("wishCheck")
	public @ResponseBody  void wishCheck(@RequestBody @RequestParam("storeNum") String storeNum, HttpSession session) {
		wishService.execute(storeNum, session);
		
	}
	
	@RequestMapping("wishList")			// 찜 목록 페이지 - 나중에 찜 목록으로 이동하는 a태그 추가해야함
	public String wishList(HttpSession session, Model model) {
		wishListService.execute(session, model);
		return "thymeleaf/member/wishList";
	}
	
	@GetMapping("memberPwModify")
	public String memberPwModify() {
		return "thymeleaf/member/myPwCon";
	}
	
	@PostMapping("memberPwModify")
	public String newPw(@RequestParam("memPw") String memPw, HttpSession session, Model model ) {
		return memberPwUpdateService.execute(session, model, memPw);
	}
	
	@PostMapping("memberPwUpdate")
	@ResponseBody // html이 아닌 값을 전달할 때 사용 
	public boolean memberPwUpdate(
			@RequestParam("oldPw") String oldPw,
			@RequestParam(value="newPw") String newPw,
			HttpSession session) {
		return myPassConfirmService.execute(newPw, oldPw, session);
	}
	
	@GetMapping("memberDelete")
	public String memberDelete(@PathVariable(value = "memNum") String memNum) {
		return "thymeleaf/member/memberDeleteChk";
	}
	
	@PostMapping("memberDelete")
	
	
}
