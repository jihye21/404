package _4.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionUpdateDBService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	StoreMapper storeMapper;
	public void execute(String maxOrder1, String deleteOrder1, String courseNum, HttpSession session) {
		
		Integer maxOrder = Integer.parseInt(maxOrder1);
		Integer deleteOrder = Integer.parseInt(deleteOrder1);
		String memberNum = userNumService.execute(session);
		
		for(int i = 1; i <= maxOrder; i++) {
			if(i < deleteOrder) {
				CourseDetailDTO dto = new CourseDetailDTO();
				dto.setCourseNum(courseNum);
				dto.setMemberNum(memberNum);
				dto.setCourseOrder(i);
				String storeNum = courseMapper.courseDetailSelectOne(dto).getStoreNum();
				dto.setStoreNum(storeNum);
				String storeName = storeMapper.storeSelectOne(storeNum).getStoreName();
				dto.setStoreName(storeName);
				session.setAttribute(memberNum + "/" + i, dto);
			}
			else if(i > deleteOrder) {
				CourseDetailDTO dto = new CourseDetailDTO();
				dto.setCourseNum(courseNum);
				dto.setMemberNum(memberNum);
				dto.setCourseOrder(i - 1);
				String storeNum = courseMapper.courseDetailSelectOne(dto).getStoreNum();
				dto.setStoreNum(storeNum);
				String storeName = storeMapper.storeSelectOne(storeNum).getStoreName();
				dto.setStoreName(storeName);
				session.setAttribute(memberNum + "/" + (i - 1), dto);
			}
		}
	}
}
