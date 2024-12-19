package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _4.command.EmployeeCommand;
import _4.service.employee.EmployeeListService;
import _4.service.employee.EmployeeRegistService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeRegistService employeeRegistService;
	@Autowired
	EmployeeListService employeeListService;
	
	
	@GetMapping("employeeList")
	public String emplist(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("empRegist")
	public String empRegist() {
		return "thymeleaf/employee/empForm";
	}
	
	@PostMapping("empRegist")
	public String empRegist(EmployeeCommand employeeCommand) {
		employeeRegistService.execute(employeeCommand);
		return "redirect:employeeList";
	}
	
}
