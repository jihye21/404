package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.domain.CourseDetailDTO;
import _4.mapper.CourseMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import _4.service.course.CourseDetailService;
import _4.service.course.CourseInsertService;
import _4.service.course.CourseListPageService;
import _4.service.course.CourseListService;
import _4.service.course.CourseSessionService;
import _4.service.course.CourseUpdateService;
import _4.service.course.SessionUpdateDBService;
import _4.service.course.SessionUpdateNewService;
import _4.service.course.SessionUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	CourseInsertService courseInsertService;
	@Autowired
	CourseListService courseListService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseSessionService courseSessionService;
	@Autowired
	CourseListPageService courseListPageService;
	@Autowired
	CourseDetailService courseDetailService;
	@Autowired
	CourseUpdateService courseUpdateService;
	@Autowired
	SessionUpdateService sessionUpdateService;
	@Autowired
	SessionUpdateDBService sessionUpdateDBService;
	@Autowired
	SessionUpdateNewService sessionUpdateNewService;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	CourseMapper courseMapper;
	
	@GetMapping("coursePage")
	public String coursePage(HttpSession session) {
		String memberNum = userNumService.execute(session);
		for(int i = 0; i < 10; i++) {
			session.removeAttribute(memberNum + "/" + i);
		}
		return "thymeleaf/course/courseForm";
	}
	
	@PostMapping("storeList")
	public String storeList(@RequestParam("courseNum") String courseNum, @RequestParam("courseCount") Integer courseOrder, Model model
			, String deleteCheck, HttpSession session) {
		courseListPageService.execute(courseNum, courseOrder, model, deleteCheck, session);
		return "thymeleaf/course/storeList";
	}
	
	@PostMapping("courseAdd")
	public @ResponseBody void courseAdd(@RequestParam("storeNum") String storeNum, @RequestParam("courseOrder") String courseOrder,
										@RequestParam("storeName") String storeName, HttpSession session, Model model) {
		String memberNum = userNumService.execute(session);
		courseSessionService.execute(storeNum, storeName, courseOrder, memberNum);
	}
	
	@PostMapping("storedStoreDelete")
	public @ResponseBody void storedStoreDelete(String storeNum, String order, HttpSession session) {
		String memberNum = userNumService.execute(session);
		session.removeAttribute(memberNum + "/" + order);
	}
	
	@PostMapping("courseInsert")
	public @ResponseBody String courseInsert(HttpSession session, @RequestParam("maxOrder") String maxOrder, @RequestParam("courseName") String courseName) {
		String answer = courseInsertService.execute(session, maxOrder, courseName);
		return answer;
	}
	
	@GetMapping("courseList")
	public String courseList(HttpSession session, Model model) {
		courseListService.execute(session, model);
		return "thymeleaf/course/courseList";
	}
	
	@GetMapping("courseDetail")
	public String courseDetail(String courseNum, Model model, HttpSession session) {
		courseDetailService.execute(courseNum, model);
		String memberNum = userNumService.execute(session);
		for(int i = 0; i < 10; i++) {
			session.removeAttribute(memberNum + "/" + i);
		}
		return "thymeleaf/course/courseDetail";
	}
	
	
	@PostMapping("courseUpdate")
	public @ResponseBody void courseUpdate(@RequestParam("maxOrder") String maxOrder
										 , @RequestParam("courseName") String courseName
										 , String courseNum
										 , String originalMaxOrder
										 , HttpSession session) {
		courseUpdateService.execute(maxOrder, courseName, courseNum, originalMaxOrder, session);
	}
	
	@PostMapping("courseDelete")
	public @ResponseBody String courseDelete(String courseNum) {
		courseMapper.courseDelete(courseNum);
		courseMapper.courseDetailDelete(courseNum);
		return "1";
	}
	
	@PostMapping("sessionUpdateDB")
	public @ResponseBody void sessionUpdateDB(String maxOrder, String courseNum, String deleteOrder
											, HttpSession session) {
		sessionUpdateDBService.execute(maxOrder, deleteOrder, courseNum, session);
	}
	
	@PostMapping("sessionUpdateNew")
	public @ResponseBody void sessionUpdateNew(String maxCount, String deleteOrder, String courseNum, String deleteStoreNum
											, HttpSession session) {
		sessionUpdateNewService.execute(maxCount, deleteOrder, courseNum, deleteStoreNum, session);
		String memberNum = userNumService.execute(session);
	}
	
	@PostMapping("sessionUpdate")
	public @ResponseBody void sessionUpdate(String courseOrder, String maxOrder, HttpSession session) {
		// 앞의 번호를 삭제한 후, 뒤에 세션의 번호를 하나씩 당기는 서비스
		sessionUpdateService.execute(courseOrder, maxOrder, session);
	}
}
