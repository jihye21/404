package _4.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.EmployeeDTO;
import _4.mapper.EmployeeMapper;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMapper.employeeAllSelect();
		model.addAttribute("list", list);
	}
}
