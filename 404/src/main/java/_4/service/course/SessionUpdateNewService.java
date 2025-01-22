package _4.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionUpdateNewService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	public void execute(String maxCount1, String deleteOrder1, String courseNum, String deleteStoreNum, HttpSession session) {
		Integer maxCount = Integer.parseInt(maxCount1);
		Integer deleteOrder = Integer.parseInt(deleteOrder1);
		String memberNum = userNumService.execute(session);
		String DB = "N";
		List<CourseDetailDTO> list = courseMapper.courseDetailSelectAll(courseNum);
		for(CourseDetailDTO dto : list) {
			if(dto.getStoreNum().equals(deleteStoreNum)) {
				DB = "Y";
			}
		}
		
		for(int i = 1; i <= maxCount; i++ ) {
			if(i < deleteOrder) {
				// 세션 유지
			}
			else if(i == deleteOrder) {
				if(DB.equals("Y")) {
					continue;
				}
				else session.removeAttribute(memberNum + "/" + i);
			}
			else if(i > deleteOrder) {
				CourseDetailDTO dto = (CourseDetailDTO) session.getAttribute(memberNum + "/" + i);
				if(dto == null) {
					continue;
				}
				dto.setCourseOrder(dto.getCourseOrder() - 1);
				session.removeAttribute(memberNum + "/" + i);
				session.setAttribute(memberNum + "/" + (i - 1), dto);
			}
		}
	}
}
