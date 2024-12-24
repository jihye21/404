package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.ThemeDTO;

@Mapper
public interface ThemeMapper {
	public void themeInsert(ThemeDTO dto);
	public List<ThemeDTO> themeSelectAll(String storeNum);
	public ThemeDTO themeSelectOne(String themeNum);
	public void themeUpdate(ThemeDTO dto);
	public void themeDelete(String themeNum);
}
