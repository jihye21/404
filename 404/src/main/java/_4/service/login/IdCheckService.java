package _4.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.LoginMapper;

@Service
public class IdCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(String memId) {
		String resultId = loginMapper.selectIdCheck(memId);
		return resultId;
	}
}
