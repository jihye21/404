package _4.service.employee;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.domain.EmployeeDTO;
import _4.mapper.EmpMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpPointRegistService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	EmpMapper empMapper;
	public boolean execute(HttpSession session, Integer empPoint) {
		
		String empNum = userNumService.execute(session);
		
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setEmpNum(empNum);
		empDTO.setEmpPoint(empPoint);
		
		//포인트 받은 날 가져오기
		Date lastPointGetDate = empMapper.lastPointGetDate(empNum);
		
		if(lastPointGetDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			Integer lastPointGetMonth = Integer.parseInt(sdf.format(lastPointGetDate));
			
			//현재 날짜
			Date currentDate = new Date();
			Integer currentMonth = Integer.parseInt(sdf.format(currentDate));
			
			if(lastPointGetMonth != currentMonth) {
				empMapper.empPointUpdate(empDTO);
			}else {
				return false;
			}
		}
		else {
			empMapper.empPointUpdate(empDTO);
		}
		return true;
	}

}
