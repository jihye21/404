package _4.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.LoginMapper;

@Service
public class EmailCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(String userEmail) {
		return loginMapper.emailCheckSelectOne(userEmail);
	}
}
