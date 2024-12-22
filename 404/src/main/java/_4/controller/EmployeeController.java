package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _4.command.EmployeeCommand;
import _4.domain.StoreApplicationDTO;
import _4.mapper.StoreApplMapper;
import _4.service.employee.EmployeeDetailService;
import _4.service.employee.EmployeeListService;
import _4.service.employee.EmployeeRegistService;
import _4.service.employee.EmployeeUpdateService;
import _4.service.store.StoreApplDeleteService;
import _4.service.store.StoreRegistService;

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
	@Autowired
	StoreRegistService storeRegistService;
	@Autowired
	StoreApplDeleteService storeApplDeleteService;
	
	@Autowired
	StoreApplMapper storeApplMapper;
	
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
	
	@GetMapping("storeApplList")
	public String storeApplList(Model model) {
		List<StoreApplicationDTO> list = storeApplMapper.storeApplSelectAll();
		model.addAttribute("list", list);
		return "thymeleaf/employee/storeApplList";
	}
	
	@GetMapping("storeApplDetail")
	public String storeApplDetail(@RequestParam("storeApplNum") String storeApplNum, Model model) {
		StoreApplicationDTO dto = storeApplMapper.storeApplSelectOne(storeApplNum);
		model.addAttribute("dto", dto);
		return "thymeleaf/employee/storeApplDetail";
	}
	
	@PostMapping("storeApplOk")
	public void storeApplOk(@RequestParam("storeApplNum") String storeApplNum, @RequestParam("category") String category) {
		storeRegistService.execute(storeApplNum, category);
		storeApplDeleteService.execute(storeApplNum);
	}
	
	@PostMapping("storeApplNo")
	public void storeApplNo(@RequestParam("storeApplNum") String storeApplNum) {
		storeApplDeleteService.execute(storeApplNum);
	}
	
}
