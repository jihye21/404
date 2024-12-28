package _4.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.EmployeeDTO;
import _4.domain.StartEndPageDTO;
import _4.mapper.EmployeeMapper;
import _4.mapper.service.StartEndPageService;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	public void execute(Model model, String searchWord, int page) {
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<EmployeeDTO> list = employeeMapper.employeeAllSelect();
		
		int count = employeeMapper.employeeCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, searchWord);
		
		model.addAttribute("list", list);
		}
}
