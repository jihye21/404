package _4.service.coupon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.CouponDTO;
import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class memberCouponListService {
	@Autowired
	CouponMapper couponMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(HttpSession session, Model model) {
		List<CouponDTO> couponList = new ArrayList<CouponDTO>();
		
		String memNum = userNumService.execute(session);
		
		couponList = couponMapper.memberCouponSelectAll(memNum);
		
		model.addAttribute("couponList", couponList);
		//오늘 날짜 
		Date today = new Date();
		model.addAttribute("today", today);
	}
}
