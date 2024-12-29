package _4.domain;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("couponDTO")
public class CouponDTO {
	String couponNum;
	String couponCode;
	String couponName;
	String discountType;
	Date expirationDate;
	Integer discountRate;
	String couponNote;
	String empNum;
	String ownerNum;
}
