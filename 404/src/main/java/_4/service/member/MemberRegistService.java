package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.MemberCommand;
import _4.domain.MemberDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.EmailSendService;

@Service
public class MemberRegistService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(MemberCommand memberCommand, Model model) {
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
		dto.setMemNickname(memberCommand.getMemNickname());
		
		int i = memberMapper.memberRegist(dto);
		model.addAttribute("userName", dto.getMemName());
		model.addAttribute("userEmail", dto.getMemEmail());
		
		if(i >= 1) {
			// 메일링
			String html = "<html><body>";
					html += dto.getMemName() + "님 가입을 환영합니다.<br />";
					html += "가입을 완료하시려면";
					html += "<a href='http://localhost:8080/userComfirm?chk=" + dto.getMemEmail() + "'>여기</a>를 클릭하세요.";
					html += "</body></html>";
				String subject = "환영 인사입니다.";
				String fromEmail = "soongmoostudent@gmail.com";
				String toEmail = dto.getMemEmail();
				
				
				emailSendService.mailSend(html, subject, fromEmail, toEmail);
		}
		
	}
	
	
}
