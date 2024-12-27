package _4.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.CouponCommand;
import _4.domain.AuthDTO;
import _4.domain.CouponDTO;
import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CouponUpdateService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CouponMapper couponMapper;
	public void execute(String couponNum, CouponCommand couponCommand, HttpSession session) {
		CouponDTO couponDTO = new CouponDTO();
		
		couponDTO.setCouponNum(couponNum);
		couponDTO.setCouponName(couponCommand.getCouponName());
		couponDTO.setDiscountType(couponCommand.getDiscountType());
		couponDTO.setDiscountRate(couponCommand.getDiscountRate());
		couponDTO.setExpirationDate(couponCommand.getExpirationDate());
		couponDTO.setCouponNote(couponCommand.getCouponNote());
		
		String userNum = userNumService.execute(session);
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		if(auth.getGrade().equals("employee")) {
			couponDTO.setEmpNum(userNum);
			couponMapper.empCouponUpdate(couponDTO);
		}else if(auth.getGrade().equals("owner")) {
			couponDTO.setOwnerNum(userNum);
			couponMapper.ownerCouponUpdate(couponDTO);
		}
		
	}
}
