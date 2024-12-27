package _4.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CouponDeleteService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CouponMapper couponMapper;
	public void execute(String couponNum, HttpSession session) {
		String userNum = userNumService.execute(session);
		couponMapper.ownerCouponDelete(couponNum, userNum);
		couponMapper.empCouponDelete(couponNum, userNum);
	}
}
