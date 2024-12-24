package _4.service.inqurie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.InquireDTO;
import _4.mapper.InquireMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	InquireMapper inquireMapper;
	
	public void execute(Model model, HttpSession session) {
		//사용자의 번호 가져오기
		String userNum = userNumService.execute(session);
		
		//문의 목록 가져오기
		List<InquireDTO> list = new ArrayList<InquireDTO>();
		
		//사용자의 grade 구분 - onwer(사장) or member(회원)
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("member")) {
			list = inquireMapper.memberInquireSelectAll(userNum);
		}else if (auth.getGrade().equals("owner")) {
			list = inquireMapper.ownerInquireSelectAll(userNum);
		}else if(auth.getGrade().equals("employee")) {
			list = inquireMapper.employeeInquireSelectAll();
		}
		
		//list로 문의 목록 보내기
		model.addAttribute("list", list);
	}
}
