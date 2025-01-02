package _4.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.ReviewCommand;
import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;

@Service
public class ReviewUpdateService {
	@Autowired
	ReviewMapper reviewMapper;
	
	public void execute(ReviewCommand reviewCommand) {
		ReviewDTO dto = new ReviewDTO();
		dto.setReviewNum(reviewCommand.getReviewNum());
		dto.setReviewContents(reviewCommand.getReviewContents());
		reviewMapper.reviewUpdate(dto);
	}
}
