package _4.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.LoginMapper;

@Service
public class NickNameCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(String memNickname) {
		String resultNick = loginMapper.selectNickCheck(memNickname);
		return resultNick;
	}
}
