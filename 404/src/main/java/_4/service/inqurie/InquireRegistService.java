package _4.service.inqurie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.InquireCommand;
import _4.domain.AuthDTO;
import _4.domain.InquireDTO;
import _4.mapper.InquireMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireRegistService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	InquireMapper inquireMapper;
	
	public void execute(InquireCommand inquireCommand, HttpSession session) {
		//사용할 DTO
		InquireDTO inquireDTO = new InquireDTO();
		
		//member 문의와 owner 문의의 공통 내용(문의 제목, 문의 내용) DTO에 저장하기
		inquireDTO.setInquireSubject(inquireCommand.getInquireSubject());
		inquireDTO.setInquireContent(inquireCommand.getInquireContent());
		
		//사용자의 번호 가져오기
		String userNum = userNumService.execute(session);
		
		//문의 번호
		String inquireNum = autoNumService.execute("memberInquire", "inquire_num", "inquire_");
		inquireDTO.setInquireNum(inquireNum);
		
		//사용자의 grade 구분
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")) {
			inquireDTO.setMemNum(userNum);
			inquireDTO.setMemberInquireType(inquireCommand.getInquireType());
			
			inquireMapper.memberInquireInsert(inquireDTO);
		}else if(auth.getGrade().equals("owner")){
			inquireDTO.setOwnerNum(userNum);
			inquireDTO.setOwnerInquireType(inquireCommand.getInquireType());
			
			inquireMapper.ownerInquireInsert(inquireDTO);
		}
		
		
	}
}
