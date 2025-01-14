package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupMemberReviewCheckService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupMapper groupMapper;
	public BookDTO execute(String bookNum, HttpSession session) {
		String memNum = userNumService.execute(session);
		
		BookDTO bookDTO = groupMapper.groupMemberReview(memNum, bookNum);
		
		return bookDTO;
	}
}
