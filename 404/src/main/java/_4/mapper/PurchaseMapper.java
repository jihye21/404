package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.PaymentDTO;

@Mapper
public interface PurchaseMapper {

	public void paymentInsert(PaymentDTO dto);
	public void paymentCheck(String bookNum);
	public void patmentCouponCheck(String bookNum);
}
