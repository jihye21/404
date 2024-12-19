package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.MemberCommand;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(String memNum, Model model, MemberCommand memberCommand) {
		MemberDTO dto = memberMapper.memberSelectOne(memNum);
		model.addAttribute("dto", dto);
	}
}
