package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDetailDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseSessionService {
	@Autowired
	HttpSession session;
	public void execute(String storeNum, String storeName, String courseOrder, String memberNum) {
		// 코스 세션에 임시 저장 (courseNum을 제외하고 먼저 저장)
		CourseDetailDTO temp = new CourseDetailDTO();
		temp.setCourseOrder(Integer.parseInt(courseOrder));
		temp.setStoreNum(storeNum);
		temp.setMemberNum(memberNum);
		temp.setStoreName(storeName);
		session.setAttribute(memberNum + "/" + courseOrder, temp);
	}
}
