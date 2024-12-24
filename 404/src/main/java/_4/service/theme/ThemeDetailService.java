package _4.service.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.ThemeDTO;
import _4.mapper.ThemeMapper;

@Service
public class ThemeDetailService {
	@Autowired
	ThemeMapper themeMapper;
	public void execute(String themeNum, Model model) {
		ThemeDTO dto = themeMapper.themeSelectOne(themeNum);
		model.addAttribute("dto", dto);
	}
}
