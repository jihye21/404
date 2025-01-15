package _4.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.GroupCommand;
import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GroupBookInfoService {
	@Autowired
	BookMapper bookMapper;
	public void execute(GroupCommand groupCommand, Model model, HttpSession session) {
		String bookNum = groupCommand.getBookNum();
		BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
		
		model.addAttribute("bookDTO", bookDTO);
	}
}
