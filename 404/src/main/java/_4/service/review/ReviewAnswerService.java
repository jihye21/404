package _4.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.ReviewCommand;
import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;

@Service
public class ReviewAnswerService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String reviewNum, String reviewAnswerContents) {
		ReviewDTO dto = new ReviewDTO();
		dto.setReviewNum(reviewNum);
		dto.setReviewAnswerContents(reviewAnswerContents);
		reviewMapper.reviewAnswerInsert(dto);
	}
}
