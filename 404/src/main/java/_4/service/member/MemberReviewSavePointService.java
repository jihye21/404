package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberReviewSavePointService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	public void execute(HttpSession session) {
		BookDTO bookDTO = new BookDTO();
		String memNum = userNumService.execute(session);
		bookDTO.setMemNum(memNum);
		//리뷰 작성 시 100 포인트 지급
		bookDTO.setMemPoint(100);
		
		bookMapper.bookMemberPointUpdate(bookDTO);
	}
}
