package _4.service.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.ThemeCommand;
import _4.domain.ThemeDTO;
import _4.mapper.ThemeMapper;

@Service
public class ThemeModifyService {
	@Autowired
	ThemeMapper themeMapper;
	public void execute(ThemeCommand themeCommand) {
		ThemeDTO dto = new ThemeDTO();
		dto.setThemeNum(themeCommand.getThemeNum());
		dto.setThemeName(themeCommand.getThemeName());
		dto.setThemeIntroduction(themeCommand.getThemeIntroduction());
		dto.setThemePrice(themeCommand.getThemePrice());
		dto.setLimitPeople(themeCommand.getLimitPeople());
		dto.setThemeTime(themeCommand.getThemeTime());
		dto.setStoreNum(themeCommand.getStoreNum());
		themeMapper.themeUpdate(dto);
		// 사진 변경 추가해야 함.
	}
}
