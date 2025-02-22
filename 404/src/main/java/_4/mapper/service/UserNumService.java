package _4.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.mapper.MainMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class UserNumService {
	@Autowired
	MainMapper mainMapper;
	public String execute(HttpSession session) {
		AuthDTO auth = (AuthDTO)session.getAttribute("auth");
		return mainMapper.userNumSelect(auth.getUserId());
	}
}
