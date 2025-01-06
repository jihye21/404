package _4.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.ReviewMapper;

@Service
public class ReviewAnswerDeleteService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String reviewNum) {
		reviewMapper.reviewAnswerDelete(reviewNum);
	}
}
