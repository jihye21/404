package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseInsertService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	public String execute(HttpSession session, String maxOrder, String courseName) {
		String memberNum = userNumService.execute(session);
		String courseNum = autoNumService.execute("course", "course_num", "course_");
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseNum(courseNum);
		courseDTO.setMemNum(memberNum);
		courseDTO.setCourseName(courseName);

		if((CourseDetailDTO)session.getAttribute(memberNum + "/" + 1) != null){
			courseMapper.courseInsert(courseDTO);
			// courseDetail에 넣기
			for(int i = 1; i <= Integer.parseInt(maxOrder); i++) {
				// maxOrder에 닿기 전에 dto가 부족하다면
				if((CourseDetailDTO)session.getAttribute(memberNum + "/" + i) == null){
					break;
				}
				CourseDetailDTO courseDetailDTO = (CourseDetailDTO)session.getAttribute(memberNum + "/" + i);
				courseDetailDTO.setCourseNum(courseNum);
				courseMapper.courseDetailInsert(courseDetailDTO);
			}
			return "Y";
		}
		else {
			return "N";
		}
	}
}
