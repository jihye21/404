package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberPwUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String execute(HttpSession session, Model model, String memPw ) {
		AuthDTO auth = (AuthDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memPw, auth.getUserPw())) {	// session에 저장한 내 비밀번호와 입력한 비밀번호가 같은지 비교 (입력한 비밀번호, 암호된 비밀번호)
			return "thymeleaf/member/myNewPw";					// 맞으면 비밀번호 변경 페이지 이동
		}else {
			// 오류에서 메시지 보내기
			model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/myPwCon";					// 틀리면 현제 패이지로 이동
		}
	}
}
