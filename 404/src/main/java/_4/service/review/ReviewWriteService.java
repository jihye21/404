package _4.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.ReviewCommand;
import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;
import _4.mapper.service.AutoNumService;

@Service
public class ReviewWriteService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	ReviewMapper reviewMapper;
	
	public void execute(ReviewCommand reviewCommand) {
		ReviewDTO dto = new ReviewDTO();
		String autoNum = autoNumService.execute("review", "review_num", "rev_");
		dto.setReviewNum(autoNum);
		dto.setMemNum(reviewCommand.getMemNum());
		dto.setReviewContents(reviewCommand.getReviewContents());
		reviewMapper.reviewInsert(dto);
	}
}
