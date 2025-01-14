package _4.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.BookDTO;
import _4.mapper.BookMapper;
import _4.mapper.GroupMapper;
import _4.mapper.service.UserNumService;
import _4.service.group.GroupCheckService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberSavePointService {
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	GroupCheckService groupCheckService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(String bookNum, String finalPrice, HttpSession session) {
		String memNum = userNumService.execute(session);
		Integer memPoint = (int)(Integer.parseInt(finalPrice) * 0.1);
		String check = bookMapper.bookMemberCheck(bookNum, memNum);
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setMemNum(memNum);
		bookDTO.setMemPoint(memPoint);
		bookDTO.setBookNum(bookNum);
		
		String groupCheck = groupCheckService.execute(bookNum);
		if(groupCheck != null) {
			//groupNum 가져오기
			bookDTO = bookMapper.bookSelectOne(bookNum);
			
			//String groupNum = 
			//모든 회원을 가져오기
			//List<groupDTO> groupMemberList = groupMapper.groupMemberSelectAll(null);
			
			//그룹 회원에게 포인트 적립하기
			//bookMapper.groupMemberPointUpdate(bookDTO);
		}
		//1인 결제 시
		if(check != null && check.equals("yes")) {
			bookMapper.bookMemberPointUpdate(bookDTO);
		}
	}
}
