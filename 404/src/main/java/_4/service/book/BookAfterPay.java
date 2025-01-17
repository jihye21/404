package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class BookAfterPay {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	GroupMapper groupMapper;
	public void execute(HttpSession session, String bookNum, Model model) {
		String memNum = userNumService.execute(session);
		
		BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
		
		String groupNum = bookDTO.getGroupNum();
		
		model.addAttribute("dto", bookDTO);
	}
}
