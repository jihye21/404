package _4.command;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CouponCommand {
	String couponName;
	//전체 할인 혹은 상품 할인
	String discountType;
	//할인율 혹은 할인 금액
	Integer discountRate;
	//쿠폰 만료일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expirationDate;
	String couponNote;
}
