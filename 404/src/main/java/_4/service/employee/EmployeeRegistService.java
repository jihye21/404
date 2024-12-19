package _4.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import _4.command.EmployeeCommand;
import _4.domain.EmployeeDTO;
import _4.mapper.EmployeeMapper;
import _4.mapper.service.AutoNumService;

@Service
public class EmployeeRegistService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AutoNumService autoNumService;
	
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		String autoNum = autoNumService.execute("employee", "emp_num", "emp_");
		dto.setEmpNum(autoNum);
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpId(employeeCommand.getEmpId());
		// dto.setEmpPw(employeeCommand.getEmpPw());
		// dto.setEmpPwCon(employeeCommand.getEmpPwCon());
		String endodePw = passwordEncoder.encode(employeeCommand.getEmpPw());
		dto.setEmpPw(endodePw);
		dto.setEmpPwCon(endodePw);
		dto.setEmpJumin(employeeCommand.getEmpJumin());
		dto.setEmpBirthDate(employeeCommand.getEmpBirthDate());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		employeeMapper.employeeRegist(dto);
	}
}
