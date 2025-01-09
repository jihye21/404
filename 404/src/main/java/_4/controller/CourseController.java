package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;

@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	StoreMapper storeMapper;
	
	@PostMapping("storeList")
	public String storeList(Model model) {
		List<StoreDTO> list = storeMapper.storeSelectAll();
		model.addAttribute("list", list);
		return "thymeleaf/course/storeList";
	}
}
