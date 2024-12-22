package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		String autoNum = autoNumService.execute("member", "mem_num", "mem_");
		dto.setMemNum(autoNum);
		dto.setMemName(memberCommand.getMemName());
		dto.setMemId(memberCommand.getMemId());
		String encodePw = passwordEncoder.encode(memberCommand.getMemPw());
		dto.setMemPw(encodePw);
		dto.setGender(memberCommand.getGender());
		dto.setMemBirthDate(memberCommand.getMemBirthDate());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemEmail(memberCommand.getMemEmail());
		memberMapper.memberRegist(dto);
		
	}
	
	
}
