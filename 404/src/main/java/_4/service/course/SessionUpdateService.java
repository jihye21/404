package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDetailDTO;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionUpdateService {
	@Autowired
	UserNumService userNumService;
	
	public void execute(String courseOrder, String maxOrder, HttpSession session) {
		String memberNum = userNumService.execute(session);

		for(int i = Integer.parseInt(courseOrder); i < Integer.parseInt(maxOrder); i++) {
			// 세션 삭제하기
			session.removeAttribute(memberNum + "/" + i);
			// 삭제하려는 세션의 다음 세션의 정보 가져오기
			CourseDetailDTO dto2 = (CourseDetailDTO)session.getAttribute(memberNum + "/" + (i + 1));
			// 다음 세션의 정보를 dto에 담기
			CourseDetailDTO dto = new CourseDetailDTO();
			dto.setCourseOrder(dto2.getCourseOrder());
			dto.setStoreNum(dto2.getStoreNum());
			dto.setStoreName(dto2.getStoreName());
			dto.setMemberNum(memberNum);
			// dto를 통해 앞당겨진 새로운 세션 만들기
			session.setAttribute(memberNum + "/" + i, dto);
		}
		session.removeAttribute(memberNum + "/" + maxOrder);
	}
}
