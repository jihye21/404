package _4.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MainMapper {
	public String autoNumSelect(@Param("tableName") String tableName, @Param("columnName") String columnName, @Param("headName") String headName);
}
