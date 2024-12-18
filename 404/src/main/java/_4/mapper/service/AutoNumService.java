package _4.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.mapper.MainMapper;

@Service
public class AutoNumService {
	@Autowired
	MainMapper mainMapper;
	public String execute(String tableName, String columnName, String headName, Model model) {
		String autoNum = mainMapper.autoNumSelect(tableName, columnName, headName);
		model.addAttribute("autoNum", autoNum);
		return autoNum;
	}
}
