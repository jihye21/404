package _4.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.command.EmployeeCommand;
import _4.domain.EmployeeDTO;
import _4.mapper.EmployeeMapper;

@Service
public class EmpDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(String empNum, Model model, EmployeeCommand employeeCommand) {
		EmployeeDTO dto = employeeMapper.employeeOneSelect(empNum);
		model.addAttribute("dto", dto);
	}
}
