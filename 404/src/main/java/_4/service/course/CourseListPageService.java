package _4.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.CourseDetailDTO;
import _4.domain.StoreDTO;
import _4.mapper.CourseMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CourseListPageService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	StoreMapper storeMapper;
	public void execute(String courseNum, Integer courseOrder, Model model, HttpSession session) {
		String memberNum = userNumService.execute(session);
		CourseDetailDTO sessionStoredDTO = (CourseDetailDTO)session.getAttribute(memberNum + "/" + courseOrder);

		CourseDetailDTO dto = new CourseDetailDTO();
		dto.setCourseNum(courseNum);
		dto.setCourseOrder(courseOrder);
		CourseDetailDTO dbStoredDTO = courseMapper.courseDetailSelectOne(dto);

		if(sessionStoredDTO != null) { // 세션에 임시 저장된 코스의 가게에 대한 정보 가져오기
			model.addAttribute("storedStoreDTO", sessionStoredDTO);// storeNum, memberNum, storeName
		}
		else {
			if(dbStoredDTO != null) { // courseDetail에서 DB에 저장된 코스의 가게 정보 가져오기
				model.addAttribute("storedStoreDTO", dbStoredDTO);// storeNum, memberNum, storeName
			}
			else { // 세션에도 DB에도 없는 코스는 가게 리스트만 뿌리기
			}
		}	
		model.addAttribute("storeCount", courseOrder);
	}
}
