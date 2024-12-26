package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("paymentDTO")
public class PaymentDTO {
	String purchaseNum;
	String confirmNumber;
	String cardNum;
	String tid;
	String totalPrice;
	String resultMessage;
	String payMethod;
	String applDate;
	String applTime;
	String purchaseName;
}
