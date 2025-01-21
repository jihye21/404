package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDetailDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseDetailSessionService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	StoreMapper storeMapper;
	public void execute(String courseNum, String storeNum, String courseOrder, HttpSession session) {
		String memberNum = userNumService.execute(session);
		String storeName = storeMapper.storeSelectOne(storeNum).getStoreName();
		// 삭제한 이후의 번호를 세션으로 다시 저장
		CourseDetailDTO dto = new CourseDetailDTO();
		dto.setCourseNum(courseNum);
		dto.setMemberNum(memberNum);
		dto.setStoreNum(storeNum);
		dto.setStoreName(storeName);
		dto.setCourseOrder(Integer.parseInt(courseOrder));
		session.setAttribute(memberNum + "/" + courseOrder, dto);
		System.out.println(session.getAttribute(memberNum + "/" + courseOrder));
	}
}
