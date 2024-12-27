package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.BookDTO;

@Mapper
public interface BookMapper {
	public void bookInsert(BookDTO dto);
	public BookDTO bookSelectOne(String bookNum);
}
