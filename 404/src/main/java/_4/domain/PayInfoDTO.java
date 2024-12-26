package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("payInfoDTO")
public class PayInfoDTO {
	//수령인 정보
	String deliveryName;
	Integer deliveryPhone;
	
	//구매자 정보
	Integer purchasePrice;
	String purchaseName;
	
	//메시지
	String message;
}
