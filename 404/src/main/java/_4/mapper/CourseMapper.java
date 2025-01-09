package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.CourseDTO;
import _4.domain.CourseDetailDTO;

@Mapper
public interface CourseMapper {
	public void courseInsert(CourseDTO dto);
	public void courseDetailInsert(CourseDetailDTO dto);
}
