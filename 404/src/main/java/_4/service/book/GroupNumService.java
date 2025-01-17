package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.BookMapper;

@Service
public class GroupNumService {
	@Autowired
	BookMapper bookMapper;
	public String execute(String bookNum) {
		String groupNum = bookMapper.groupNumSelectOne(bookNum);

		return groupNum;
	}
}
