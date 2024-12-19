package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.EmployeeCommand;
import _4.service.employee.EmployeeDetailService;
import _4.service.employee.EmployeeUpdateService;
import _4.service.employee.EmployeeListService;
import _4.service.employee.EmployeeRegistService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeRegistService employeeRegistService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	
	@GetMapping("employeeList")
	public String emplist(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("employeeRegist")
	public String empRegist() {
		return "thymeleaf/employee/employeeForm";
	}
	
	@PostMapping("employeeRegist")
	public String empRegist(EmployeeCommand employeeCommand) {
		employeeRegistService.execute(employeeCommand);
		return "redirect:employeeList";
	}
	
	@GetMapping("employeeDetail")
	public String empDetail(String empNum, Model model, EmployeeCommand employeeCommand) {
		employeeDetailService.execute(empNum, model, employeeCommand);
		return "thymeleaf/employee/employeeInfo";
	}
	
	@GetMapping("employeeModify")
	public String empUpdate(EmployeeCommand employeeCommand) {
		return "thymeleaf/employee/employeeModify";
	}
	
	@PostMapping("employeeModify")
	public String empModify(EmployeeCommand employeeCommand) {
		employeeUpdateService.execute(employeeCommand);
		return "redirect:employeeDetail?empNum=" + employeeCommand.getEmpNum();
	}
	
	@GetMapping("employeeMainPage")
	public String employeeMainPage() {
		return "thymeleaf/employee/employeeMainPage";
	}
}
