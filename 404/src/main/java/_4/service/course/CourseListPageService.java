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
		// 세션에 저장된 가게 정보 가져오기
		String memberNum = userNumService.execute(session);
		CourseDetailDTO sessionStoredDTO = (CourseDetailDTO)session.getAttribute(memberNum + "/" + courseOrder);
		// 세션에 담긴 정보 : storeNum, storeName, memberNum, courseOrder
		
		// DB에 저장된 가게 정보 가져오기
		CourseDetailDTO dto = new CourseDetailDTO();
		dto.setCourseNum(courseNum);
		dto.setCourseOrder(courseOrder);
		CourseDetailDTO dbStoredDTO = courseMapper.courseDetailSelectOne(dto);
		
		if(sessionStoredDTO != null) { // 세션에 임시 저장된 코스의 가게에 대한 정보 가져오기
			StoreDTO sessionStoredDetailDTO = storeMapper.storeSelectOne(sessionStoredDTO.getStoreNum());
			model.addAttribute("storedStoreDTO", sessionStoredDTO);// storeNum, memberNum, storeName
			model.addAttribute("storedDetailDTO", sessionStoredDetailDTO);
		}
		else {
			if(dbStoredDTO != null) { // courseDetail에서 DB에 저장된 코스의 가게 정보 가져오기
				StoreDTO dbStoredDetailDTO = storeMapper.storeSelectOne(dbStoredDTO.getStoreNum());
				model.addAttribute("storedStoreDTO", dbStoredDTO);// storeNum, memberNum, storeName
				model.addAttribute("storedDetailDTO", dbStoredDetailDTO);
			}
		}	
		model.addAttribute("storeCount", courseOrder);
	}
}
