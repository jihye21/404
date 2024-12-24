package _4.service.inqurie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.InquireCommand;
import _4.domain.AuthDTO;
import _4.domain.InquireDTO;
import _4.mapper.InquireMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireUpdateService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	UserNumService userNumService;
	
	public void execute(InquireCommand inquireCommand, HttpSession session) {
		//사용자 번호
		String userNum = userNumService.execute(session);
		
		//공통 내용 저장
		InquireDTO inquireDTO = new InquireDTO();
		inquireDTO.setInquireSubject(inquireCommand.getInquireSubject());
		inquireDTO.setInquireContent(inquireCommand.getInquireContent());
		inquireDTO.setInquireNum(inquireCommand.getInquireNum());
	
		//grade 별 분류
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")) {
			inquireDTO.setMemNum(userNum);
			inquireDTO.setMemberInquireType(inquireCommand.getInquireType());
			
			inquireMapper.memberInquireUpdate(inquireDTO);
		}else if(auth.getGrade().equals("owner")) {
			inquireDTO.setOwnerNum(userNum);
			inquireDTO.setOwnerInquireType(inquireCommand.getInquireType());
			
			inquireMapper.ownerInquireUpdate(inquireDTO);
		}else if(auth.getGrade().equals("employee")) {
			inquireDTO.setEmpNum(userNum);
			inquireDTO.setInquireAnswer(inquireCommand.getInquireAnswer());
			inquireMapper.employeeMemberInquireUpdate(inquireDTO);
			inquireMapper.employeeOwnerInquireUpdate(inquireDTO);
		}
		
	}
}
