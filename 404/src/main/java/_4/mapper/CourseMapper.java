package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;

@Mapper
public interface CourseMapper {
	public void courseInsert(CourseDTO dto);
	public void courseDetailInsert(CourseDetailDTO dto);
	
	public List<CourseDTO> courseSelectAll(String memberNum);
	public List<CourseDetailDTO> courseDetailSelectAll(String courseNum);
	
	public CourseDTO courseSelectOne(String courseNum);
	public CourseDetailDTO courseDetailSelectOne(CourseDetailDTO dto);
	
	public void courseNameUpdate(CourseDTO dto);
	public void courseDetailMerge(CourseDetailDTO dto);
	
	public void courseDelete(String courseNum);
	public void courseDetailDelete(String courseNum);
}
