package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	String empId;
	String empPw;
	String empPwCon;
	String empName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empBirthDate;
	String empEmail;
	String empPhone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empHireDate;
	String empJumin;
	Integer empPoint;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date pointGetDate;
}
