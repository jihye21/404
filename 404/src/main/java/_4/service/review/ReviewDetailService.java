package _4.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.ReviewCommand;
import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;

@Service
public class ReviewDetailService {
	@Autowired
	ReviewMapper reviewMapper;
	
	public void execute(String reviewNum, Model model) {
		ReviewDTO dto = reviewMapper.reviewSelectOne(reviewNum);
		model.addAttribute("dto", dto);
	}
}
