package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.BookCommand;
import _4.domain.BookDTO;
import _4.domain.ThemeDTO;
import _4.mapper.BookMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class ThemeBookInsertService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	ThemeMapper themeMapper;
	@Autowired
	BookMapper bookMapper;
	public String execute(BookCommand bookCommand, HttpSession session) {
		String bookNum = autoNumService.execute("book", "book_num", "book_");
		String memberNum = userNumService.execute(session);
		ThemeDTO themeInfo = themeMapper.themeSelectOne(bookCommand.getThemeNum());
		String storeNum = themeInfo.getStoreNum();
		BookDTO dto = new BookDTO();
		dto.setBookNum(bookNum);
		dto.setMemNum(memberNum);
		dto.setStoreNum(storeNum);
		dto.setThemeNum(bookCommand.getThemeNum());
		dto.setThemeTime(bookCommand.getThemeTime());
		dto.setPeople(bookCommand.getPeople());
		dto.setPrice(bookCommand.getPrice());
		dto.setCouponNum(bookCommand.getCouponNum());
		//dto.setBookStatus("결제대기중");
		bookMapper.bookInsert(dto);
		return bookNum;
	}
}
