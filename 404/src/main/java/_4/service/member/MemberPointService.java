package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberPointService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(HttpSession session, Model model) {
		String memNum = userNumService.execute(session);
		
		MemberDTO memberDTO = memberMapper.memberSelectOne(memNum);
		
		Integer memPoint = memberDTO.getMemPoint();
		
		model.addAttribute("memPoint", memPoint);
	}
}
