package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupDutchService {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public boolean execute(String bookNum, HttpSession session) {
		String memNum = userNumService.execute(session);
		
		//그룹 결제인지 확인하기
		BookDTO bookDTO =  bookMapper.bookSelectOne(bookNum);
		if(bookDTO.getGroupNum().contains("group")) {
			return true;
		}
		
		return false;
	}
}
