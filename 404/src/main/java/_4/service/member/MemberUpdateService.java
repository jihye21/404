package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.MemberCommand;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;

@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(MemberCommand memberCommand, Model model) {
		MemberDTO dto = new MemberDTO();
		dto.setMemNum(memberCommand.getMemNum());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemPhone(memberCommand.getMemPhone());
		memberMapper.memberUpdate(dto);
	}
}
