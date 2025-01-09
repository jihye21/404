package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import _4.service.course.CourseInsertService;
import _4.service.course.CourseSessionService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	CourseInsertService courseInsertService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	CourseSessionService courseSessionService;
	@Autowired
	StoreMapper storeMapper;
	
	@GetMapping("coursePage")
	public String coursePage(HttpSession session) {
		String memberNum = userNumService.execute(session);
		for(int i = 0; i < 10; i++) {
			session.removeAttribute(memberNum + "/" + i);
		}
		
		return "thymeleaf/naverMap";
	}
	
	@PostMapping("storeList")
	public String storeList(@RequestParam("courseCount") Integer storeCount, Model model) {
		List<StoreDTO> list = storeMapper.storeSelectAll();
		model.addAttribute("list", list);
		model.addAttribute("storeCount", storeCount);
		return "thymeleaf/course/storeList";
	}
	
	@PostMapping("courseAdd")
	public @ResponseBody void courseAdd(@RequestParam("storeNum") String storeNum, @RequestParam("courseOrder") String courseOrder, HttpSession session, Model model) {
		String memberNum = userNumService.execute(session);
		courseSessionService.execute(storeNum, courseOrder, memberNum);
	}
	
	@PostMapping("courseInsert")
	public @ResponseBody void courseInsert(HttpSession session, @RequestParam("maxOrder") String maxOrder) {
		courseInsertService.execute(session, maxOrder);
	}
}
