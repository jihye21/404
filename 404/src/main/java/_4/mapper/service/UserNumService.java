package _4.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.MainMapper;

@Service
public class UserNumService {
	@Autowired
	MainMapper mainMapper;
	public String execute(String userId) {
		return mainMapper.userNumSelect(userId);
	}
}
