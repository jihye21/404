package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {

	public void reviewInsert(ReviewDTO dto);
	public void reviewAnswerInsert(ReviewDTO dto);
	
	public List<ReviewDTO> reviewSelectAll(String reviewNum);
	public ReviewDTO reviewSelectOne(String reviewNum);
	public ReviewDTO reviewSelectOneWithBookNum(String bookNum);

	public void reviewUpdate(ReviewDTO dto);
	public void reviewAnswerUpdate(ReviewDTO dto);

	public void reviewDelete(String reviewNum);
	public void reviewAnswerDelete(String reviewNum);
	
	

}
