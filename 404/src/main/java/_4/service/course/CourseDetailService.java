package _4.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.service.UserNumService;

@Service
public class CourseDetailService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	public void execute(String courseNum, Model model) {
		List<CourseDetailDTO> cdList = courseMapper.courseDetailSelectAll(courseNum);
		CourseDTO cDTO= courseMapper.courseSelectOne(courseNum);
		model.addAttribute("cDTO", cDTO);
		model.addAttribute("cdList", cdList);
		model.addAttribute("maxOrder", cdList.size());
	}
}
