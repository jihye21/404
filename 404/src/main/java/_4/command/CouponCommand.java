package _4.command;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CouponCommand {
	//전체 할인 혹은 상품 할인
	String discountType;
	//할인율 혹은 할인 금액
	Integer discountNum;
	//쿠폰 만료일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expireDate;
}
