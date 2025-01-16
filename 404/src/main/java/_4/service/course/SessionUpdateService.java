package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionUpdateService {
	@Autowired
	UserNumService userNumService;
	public void execute(HttpSession session) {
		String memberNum = userNumService.execute(session);
		
		
	}
}
