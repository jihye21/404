package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.EmployeeCommand;
import _4.service.employee.EmpDetailService;
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
	EmpDetailService empDetailService;
	
	
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
		empDetailService.execute(empNum, model, employeeCommand);
		return "thymeleaf/employee/employeeInfo";
	}
	
	@GetMapping("employeeMainPage")
	public String employeeMainPage() {
		return "thymeleaf/employee/employeeMainPage";
	}
}
