package _4.service.review;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String filrDir = resource.getFile();
		
		MultipartFile mf = reviewCommand.getReviewImage();
		String originalFile = mf.getOriginalFilename();
		
		String extension = originalFile.substring(originalFile.lastIndexOf("."));
		String reviewName = UUID.randomUUID().toString().replace("-", "");
		String reviewFileName = reviewName + extension;
		
		File file = new File(filrDir + "/" + reviewFileName);
		try {
			mf.transferTo(file);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		dto.setReviewImage(originalFile);
		dto.setReviewStoreImage(reviewFileName);
		
		reviewMapper.reviewInsert(dto);

	}
}
