package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.BookMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupCheckService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	public String execute(String bookNum) {
		String groupCheck = bookMapper.bookGroupCheckSelectOne(bookNum);
		
		return groupCheck;
	}
}
