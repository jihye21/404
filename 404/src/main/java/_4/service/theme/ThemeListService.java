package _4.service.theme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.ThemeDTO;
import _4.mapper.ThemeMapper;

@Service
public class ThemeListService {
	@Autowired
	ThemeMapper themeMapper;
	public void execute(String storeNum, Model model) {
		List<ThemeDTO> list = themeMapper.themeSelectAll(storeNum);
		model.addAttribute("list", list);
	}
}
