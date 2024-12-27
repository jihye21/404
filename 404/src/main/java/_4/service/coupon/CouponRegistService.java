package _4.service.coupon;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import _4.command.CouponCommand;
import _4.domain.AuthDTO;
import _4.domain.CouponDTO;
import _4.mapper.CouponMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class CouponRegistService {
	@Autowired
	UserNumService userNumService;
	@Autowired
	CouponMapper couponMapper;
	public void execute(CouponCommand couponCommand, HttpSession session) {
		CouponDTO couponDTO = new CouponDTO();
		
		couponDTO.setCouponName(couponCommand.getCouponName());
		couponDTO.setDiscountType(couponCommand.getDiscountType());
		couponDTO.setDiscountRate(couponCommand.getDiscountRate());
		
		couponDTO.setExpirationDate(couponCommand.getExpirationDate());
		couponDTO.setCouponNote(couponCommand.getCouponNote());
		
		//쿠폰 번호 발급하기
		String couponNum = UUID.randomUUID().toString();
		couponDTO.setCouponNum(couponNum);
		
		//사장님 쿠폰과 직원 쿠폰으로 분리하기
		AuthDTO auth = (AuthDTO) session.getAttribute("auth");
		String userNum = userNumService.execute(session);
		if(auth.getGrade().equals("employee")) {
			couponDTO.setEmpNum(userNum);
			couponMapper.empCouponRegistInsert(couponDTO);
		}else if(auth.getGrade().equals("owner")) {
			couponDTO.setOwnerNum(userNum);
			couponMapper.ownerCouponRegistInsert(couponDTO);
		}
		
	}
}
