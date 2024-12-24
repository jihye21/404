package _4.service.inqurie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.mapper.InquireMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireCheckService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	UserNumService userNumService;
	
	public String execute(HttpSession session, String inquireNum) {
		//질문에 답변이 있는지 알려줄 변수
		String result = "";
		
		//사용자의 번호 가져오기
		String userNum = userNumService.execute(session);
		
		//grade 분류
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")) {
			result = inquireMapper.memberInquireCheckService(userNum, inquireNum);
		}else if(auth.getGrade().equals("owner")){
			result = inquireMapper.ownerInquireCheckService(userNum, inquireNum);
		}else if(auth.getGrade().equals("employee")){
			result = inquireMapper.employeeInquireCheckService(inquireNum);
		}
		
		return result;
	}
}
