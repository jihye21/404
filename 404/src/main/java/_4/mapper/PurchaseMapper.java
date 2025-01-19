package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.PaymentDTO;

@Mapper
public interface PurchaseMapper {

	public void paymentInsert(PaymentDTO dto);
	public void paymentCheck(String bookNum);
	public void patmentCouponCheck(String bookNum);
	public void groupPaymentCheck(String bookNum, String memNum);
	public String groupPaySuccess(String bookNum);
	public void themeTimeBookStatusUpdate(String purchaseNum);
	public void paymentPointCheck(String bookNum, String memNum);
	public void memberPointUpdate(String bookNum, String memNum);
	public void afterPaySuccess(String bookNum);
	public void pointStatusUpdate(String bookNum, String memNum);
}
