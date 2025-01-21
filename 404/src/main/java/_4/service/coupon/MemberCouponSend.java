package _4.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.CouponMapper;
import _4.mapper.FriendMapper;

@Service
public class MemberCouponSend {
	@Autowired
	FriendMapper friendMapper;
	@Autowired
	CouponMapper couponMapper;
	public void execute(String memNickname, String couponCode) {
		String memNum = friendMapper.changeNum(memNickname);
		String couponNum = couponMapper.couponNumSelect(couponCode);
		couponMapper.memberCouponSend(memNum, couponNum);
	}
}
