package _4.service.help;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.UserChangePasswodDTO;
import _4.mapper.FindMapper;
import _4.mapper.UserMapper;
import _4.mapper.service.EmailSendService;

@Service
public class FindPwService {
	@Autowired
	FindMapper findMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmailSendService emailSendService;
	
	public void execute(String userId, String userPhone, Model model) {
		String newPw = UUID.randomUUID().toString().substring(0, 8);
		UserChangePasswodDTO dto = new UserChangePasswodDTO();
		dto.setUserId(userId);
		dto.setUserPhone(userPhone);
		dto.setUserPw(passwordEncoder.encode(newPw));
		
		AuthDTO auth = userMapper.loginSelectOne(userId);
		
		if(auth.getGrade().equals("member")) {
			dto.setTableName("member");
			dto.setPwColumName("mem_pw");
			dto.setUserIdColumName("mem_id");
			dto.setPhoneColumn("mem_phone");
		}else if(auth.getGrade().equals("employee")) {
			dto.setTableName("employee");
			dto.setPwColumName("emp_pw");
			dto.setUserIdColumName("emp_id");
			dto.setPhoneColumn("emp_phone");
		}
		
		int i = findMapper.pwUpdate(dto);
		model.addAttribute("auth", auth);
		model.addAttribute("newPw", auth);
		
		if(i > 0) {
			String html = "<html><body>";
					html += auth.getUserName() + "님의 임시 비밀번호는 " + newPw + "입니다.";
					html += "</body></html>";
			String subject = auth.getUserName() + "님의 임시 비밀번호입니다.";
			String fromEmail = "soongmoostudent@gmail.com";
			String toEmail = auth.getUserEmail();
			emailSendService.mailSend(fromEmail, toEmail, subject, newPw);
			
		}
		
	}
}
















