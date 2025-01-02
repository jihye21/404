package _4.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;
	
	public void execute(Model model) {
		List<ReviewDTO> list = reviewMapper.reviewSelectAll();
		model.addAttribute("list", list);
	}
}
