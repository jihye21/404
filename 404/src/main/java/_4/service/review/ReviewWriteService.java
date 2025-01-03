package _4.service.review;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.ReviewCommand;
import _4.domain.ReviewDTO;
import _4.mapper.ReviewMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class ReviewWriteService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	ReviewMapper reviewMapper;
	
	public void execute(ReviewCommand reviewCommand, HttpSession session) {
		String memberNum = userNumService.execute(session);
		ReviewDTO dto = new ReviewDTO();
		String reviewNum = autoNumService.execute("review", "review_num", "review_");
		dto.setReviewNum(reviewNum);
		dto.setBookNum(reviewCommand.getBookNum());
		dto.setReviewContents(reviewCommand.getReviewContents());
		dto.setMemNum(memberNum);
		dto.setStoreNum(reviewCommand.getStoreNum());
		dto.setStarRate(reviewCommand.getStarRate());
		reviewMapper.reviewInsert(dto);

	}
}
