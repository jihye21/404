package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.MemberDTO;
import _4.domain.PointDTO;
import _4.mapper.MemberMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class PointUseService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	MemberMapper memberMapper;
	public void execute(HttpSession session, String bookNum, Integer usedPoint) {
		String memNum = userNumService.execute(session);
		
		//사용된 포인트 계산하기
		MemberDTO memberDTO = memberMapper.memberSelectOne(memNum);
		Integer memPoint = memberDTO.getMemPoint();
		usedPoint = usedPoint - memPoint;
		
		PointDTO pointDTO = new PointDTO();
		pointDTO.setBookNum(bookNum);
		pointDTO.setMemNum(memNum);
		pointDTO.setUsedPoint(usedPoint);
		
		memberMapper.pointUseInsert(pointDTO);
	}
}
