package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {

	public void reviewInsert(ReviewDTO dto);

	public List<ReviewDTO> reviewSelectAll();

	public ReviewDTO reviewSelectOne(String memNum);

	public void reviewUpdate(ReviewDTO dto);

	public void reviewDelete(String reviewNum);

}
