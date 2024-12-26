package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class WishCheckService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	StoreMapper storeMapper;
	
	public void execute(StoreDTO storeDTO, HttpSession session, Model model) {
		String storeNum = storeDTO.getStoreNum();
		
		//auth가 null일 경우
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth == null) {
			String wishCheck = null;
			
			model.addAttribute("wishCheck", wishCheck);
		}
		//member일 경우
		else if(auth.getGrade().equals("member")) {
			String memNum = userNumService.execute(session);
			String wishCheck = storeMapper.WishCheck(memNum, storeNum);
			
			model.addAttribute("wishCheck", wishCheck);
		}
	}

}
