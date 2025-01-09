package _4.service.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	public void execute(HttpSession session, Model model) {
		String memberNum = userNumService.execute(session);
		List<CourseDTO> courseList = courseMapper.courseSelectAll(memberNum);
		model.addAttribute("courseList", courseList);
		List<List<CourseDetailDTO>> courseDetailTotalList = new ArrayList<>();
		for(CourseDTO dto : courseList) {
			List<CourseDetailDTO> list = courseMapper.courseDetailSelectAll(dto.getCourseNum());
			courseDetailTotalList.add(list);
		}
		model.addAttribute("courseDetailTotalList", courseDetailTotalList);
	}
}
