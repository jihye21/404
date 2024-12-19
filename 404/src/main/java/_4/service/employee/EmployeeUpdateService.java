package _4.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.EmployeeCommand;
import _4.domain.EmployeeDTO;
import _4.mapper.EmployeeMapper;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		employeeMapper.employeeUpdate(dto);
	}
}
