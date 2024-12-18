package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.MemberCommand;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.AutoNumService;

@Service
public class MemberRegistService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemberMapper memberMapper; 
	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		String autoNum = autoNumService.execute("member", "mem_num", "mem_", null);
		dto.setMemNum(autoNum);
		dto.setMemName(memberCommand.getMemName());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemPw(memberCommand.getMemPw());
		dto.setMemPwCon(memberCommand.getMemPwCon());
		dto.setMemBirth(memberCommand.getMemBirth());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemEmail(memberCommand.getMemEmail());
		memberMapper.memberRegist(dto);
		
	}
	
	
}
