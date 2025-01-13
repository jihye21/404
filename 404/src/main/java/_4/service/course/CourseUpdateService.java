package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseUpdateService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	public void execute(String maxOrder, String courseName, String courseNum, String originalMaxOrder, HttpSession session) {
		String memberNum = userNumService.execute(session);
		// course의 courseName 수정하기
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseNum(courseNum);
		courseDTO.setMemNum(memberNum);
		courseDTO.setCourseName(courseName);
		courseMapper.courseNameUpdate(courseDTO);
		/// 기존에 저장된 내용 수정하기
		for(int i = 1; i < Integer.parseInt(maxOrder); i++) {
			// maxOrder에 닿기 전에 dto가 부족하다면
			if((CourseDetailDTO)session.getAttribute(memberNum + "/" + i) == null){
				System.out.println("if문");
				continue;
			}
			System.out.println((CourseDetailDTO)session.getAttribute(memberNum + "/" + i));
			CourseDetailDTO courseDetailDTO = (CourseDetailDTO)session.getAttribute(memberNum + "/" + i);
			courseDetailDTO.setCourseNum(courseNum);
			courseMapper.courseDetailMerge(courseDetailDTO);
		}
	}
}
