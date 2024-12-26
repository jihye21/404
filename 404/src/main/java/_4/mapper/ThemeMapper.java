package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.ThemeDTO;
import _4.domain.ThemeTimeDTO;

@Mapper
public interface ThemeMapper {
	public void themeInsert(ThemeDTO dto);
	public void themeTimeInsert(@Param("themeNum") String themeNum, @Param("themeTime") String themeTime);
	public List<ThemeDTO> themeSelectAll(String storeNum);
	public List<ThemeTimeDTO> themeTimeSelectAll(String themeNum);
	public ThemeDTO themeSelectOne(String themeNum);
	public void themeUpdate(ThemeDTO dto);
	public void themeDelete(String themeNum);
}
