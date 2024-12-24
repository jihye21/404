package _4.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.mapper.FindMapper;

@Service
public class FindIdService {
	@Autowired
	FindMapper findMapper;
	
	public void execute(String userName, String userPhone, Model model) {
		String userId = findMapper.findId(userName, userPhone);
		model.addAttribute("userId", userId);
	}
}
