package _4.service.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.StoreMapper;

@Service
public class OwnerLoginService {
	@Autowired
	StoreMapper storeMapper;
	
	public String execute(String ownerNum) {
		if(storeMapper.checkStoreAppl(ownerNum) != null) { // 가게 신청서를 제출했다면
			return "redirect:/owner/ownerMainPage";
			/*if(grade == "1") { // 승인이 되었다면
				return "redirect:/owner/ownerMainPage";
			}
			else { // 승인이 되지 않았다면
				
			}*/
		}
		else { // 가게 신청서를 제출하지 않았다면
			return "thymeleaf/owner/ownerStoreApplication";
		}
	}
}
