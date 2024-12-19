package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import _4.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeRegist(EmployeeDTO dto);
	public List<EmployeeDTO> employeeAllSelect();
	public EmployeeDTO employeeOneSelect(String empNum);
	public int employeeUpdate(EmployeeDTO dto);
}
