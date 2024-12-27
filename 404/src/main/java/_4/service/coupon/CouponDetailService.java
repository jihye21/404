package _4.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.CouponDTO;
import _4.mapper.CouponMapper;

@Service
public class CouponDetailService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(String couponNum, Model model) {
		CouponDTO couponDTO = new CouponDTO();
		
		couponDTO = couponMapper.couponSelectOne(couponNum);
		
		model.addAttribute("dto", couponDTO);
	}
}
