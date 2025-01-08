package _4.service.store;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.DepositCommand;
import _4.domain.DepositDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class DepositSettingService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	StoreMapper storeMapper;
	
	public void execute(DepositCommand depositCommand, HttpSession session) {
		DepositDTO dto = new DepositDTO();
		String ownerNum = userNumService.execute(session);
		String storeNum = storeMapper.storeSelectOne(ownerNum).getStoreNum();
		if(storeMapper.depositSettingSelect(storeNum) != null) {
			// 예약금이 설정되어 있어서 수정하는 경우
			int count = 0;
			for(String a : depositCommand.getEndPrice()) {
				count ++;
			}
			for(int i = 0; i < count; i++) {
				dto.setDepositNum(i + 1);
				dto.setStoreNum(storeNum);
				dto.setStartPrice(depositCommand.getStartPrice()[i]);
				dto.setEndPrice(depositCommand.getEndPrice()[i]);
				dto.setDepositRate(depositCommand.getDepositRate()[i]);		
				storeMapper.depositSettingUpdate(dto);
			}
		}
		else {
			// 예약금이 설정되지 않아 삽입하는 경우
		}
		
	}
}
