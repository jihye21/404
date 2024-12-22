package _4.service.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.StoreDTO;
import _4.mapper.StoreApplMapper;
import _4.mapper.StoreMapper;

@Service
public class OwnerLoginService {
	@Autowired
	StoreApplMapper storeApplMapper;
	@Autowired
	StoreMapper storeMapper;
	
	public String execute(String ownerNum) {
		StoreDTO dto = storeMapper.storeSelectOne(ownerNum);
		if(dto != null) {
			return "redirect:/owner/ownerMainPage";
		}
		else {
			if(storeApplMapper.checkStoreAppl(ownerNum) != null) {
				return "thymeleaf/owner/ownerApplyWait";
			}
			else {
				return "thymeleaf/owner/ownerStoreApplication";
			}
		}
	}     
}
