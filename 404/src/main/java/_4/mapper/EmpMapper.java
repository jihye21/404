package _4.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.EmployeeDTO;

@Mapper
public interface EmpMapper {

	public void empPointUpdate(EmployeeDTO empDTO);

	public Date lastPointGetDate(String empNum);
	
}
