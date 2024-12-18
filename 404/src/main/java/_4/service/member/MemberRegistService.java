package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.MemberCommand;
import _4.domain.MemberDTO;

@Service
public class MemberRegistService {
	@Autowired
	MemberMapper memberMapper; 
	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setMemNum(memberCommand.getMemNum());
	}
}
