package _4.service.inqurie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.InquireDTO;
import _4.mapper.InquireMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireDetailService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	InquireMapper inquireMapper;
	
	public void execute(Model model, HttpSession session, String inquireNum) {
		//사용할 DTO
		InquireDTO inquireDTO = new InquireDTO();
		
		//사용자의 번호
		String userNum = userNumService.execute(session);
		
		//사용자의 grade
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")) {
			inquireDTO = inquireMapper.memberInquireSelect(userNum, inquireNum);
		}else if(auth.getGrade().equals("owner")) {
			inquireDTO = inquireMapper.ownerInquireSelect(userNum, inquireNum);
		}else if(auth.getGrade().equals("employee")) {
			inquireDTO = inquireMapper.employeeInquireSelect(inquireNum);
		}
		
		model.addAttribute("dto", inquireDTO);
	}
}
