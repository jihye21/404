package _4.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.CouponCommand;
import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberCouponRegistService {
	@Autowired
	CouponMapper couponMapper;
	@Autowired
	UserNumService userNumService;
	public void execute(HttpSession session, CouponCommand couponCommand) {
		String memNum = userNumService.execute(session);
		String couponCode = couponCommand.getCouponCode();
		
		String couponNum = couponMapper.couponNumSelect(couponCode);
		
		//이미 등록된 쿠폰인지 확인
		String couponCheck = couponMapper.memberCouponRegistCheck(memNum, couponNum);
		
		if(!"발급된 쿠폰".equals(couponCheck)) {
			couponMapper.memberCouponInsert(memNum, couponNum);
		}
		
	}
}
