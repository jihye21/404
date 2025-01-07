package _4.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.UserMapper;

@Service
public class UserEmailCheckService {
	@Autowired
	UserMapper userMapper;
	
	public int execute(String email) {
		int i = userMapper.userCheckUpdate(email);
		return i;
	}
}
