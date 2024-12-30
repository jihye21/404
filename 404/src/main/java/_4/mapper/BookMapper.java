package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.BookDTO;

@Mapper
public interface BookMapper {
	public void bookInsert(BookDTO dto);
	public BookDTO bookSelectOne(String bookNum);
	public List<BookDTO> bookSelectAllWithMember(String memberNum);
	public List<BookDTO> bookSelectAllWithStore(String storeNum);
	public void waitedBookDelete(String memberNum);
}
