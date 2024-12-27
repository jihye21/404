package _4.service.coupon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.AuthDTO;
import _4.domain.CouponDTO;
import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CouponListService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CouponMapper couponMapper;
	public void execute(HttpSession session, Model model) {
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		
		String userNum = userNumService.execute(session);
		List<CouponDTO> list = new ArrayList<CouponDTO>();
		
		if(auth.getGrade().equals("employee")) {
			 list = couponMapper.empCouponSelectAll(userNum);
		}else if(auth.getGrade().equals("owner")){
			list = couponMapper.ownerCouponSelectAll(userNum);
		}
		
		model.addAttribute("list", list);
	}
}
