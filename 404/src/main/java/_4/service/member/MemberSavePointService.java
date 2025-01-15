package _4.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
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
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setMemNum(memNum);
		bookDTO.setMemPoint(memPoint);
		bookDTO.setBookNum(bookNum);
		
		//그룹일 때
		String groupCheck = groupCheckService.execute(bookNum);
		if(groupCheck != null) {
			//groupNum 가져오기
			bookDTO = bookMapper.bookSelectOne(bookNum);
			String groupNum = bookDTO.getGroupNum();
			
			//타입 변환
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.setGroupNum(groupNum);
			
			//모든 회원을 가져오기
			List<GroupDTO> groupMemberList = groupMapper.groupMemberSelectAll(groupDTO);
			
			//그룹 회원에게 포인트 적립하기
			for(GroupDTO gm : groupMemberList) {
				bookDTO = new BookDTO();
				
				bookDTO.setMemNum(gm.getMemNum());
				bookDTO.setMemPoint(memPoint);
				
				bookMapper.bookMemberPointUpdate(bookDTO);
			}
		}else {
			//1인 결제 시
			String check = bookMapper.bookMemberCheck(bookNum, memNum);
			if(check != null && check.equals("yes")) {
				bookMapper.bookMemberPointUpdate(bookDTO);
			}
		}
		
	}
}
