package _4.service.theme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.ThemeDTO;
import _4.domain.ThemeTimeDTO;
import _4.mapper.ThemeMapper;

@Service
public class ThemeListService {
	@Autowired
	ThemeMapper themeMapper;
	public void execute(String storeNum, Model model) {
		List<ThemeDTO> themeList = themeMapper.themeSelectAll(storeNum);
		List<List<ThemeTimeDTO>> themeListTotal = new ArrayList<>();
		for(ThemeDTO dto : themeList) {
			List<ThemeTimeDTO> themeTimeList = themeMapper.themeTimeSelectAll(dto.getThemeNum());
			themeListTotal.add(themeTimeList);
		}
		model.addAttribute("themeList", themeList);
		model.addAttribute("themeTimeTotalList", themeListTotal);
	}
}
