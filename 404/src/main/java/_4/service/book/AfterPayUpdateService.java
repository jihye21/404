package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.BookCommand;
import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class AfterPayUpdateService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	public void execute(HttpSession session, BookCommand bookCommand) {
		String memNum = userNumService.execute(session);
		
		String bookNum = bookCommand.getBookNum();
		Integer afterPrice = bookCommand.getAfterPrice();
		Integer afterDutchPrice = bookCommand.getAfterDutchPrice();
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setMemNum(memNum);
		bookDTO.setBookNum(bookNum);
		bookDTO.setAfterPrice(afterPrice);
		bookDTO.setAfterDutchPrice(afterDutchPrice);
		
		bookMapper.afterPayUpdate(bookDTO);
	}
}
