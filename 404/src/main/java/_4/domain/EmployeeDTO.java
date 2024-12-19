package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("empDTO")
public class EmployeeDTO {
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
}
