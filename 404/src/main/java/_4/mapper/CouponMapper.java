package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.CouponDTO;

@Mapper
public interface CouponMapper {

	public void empCouponRegistInsert(CouponDTO couponDTO);

	public void ownerCouponRegistInsert(CouponDTO couponDTO);

	public List<CouponDTO> empCouponSelectAll(String userNum);

	public List<CouponDTO> ownerCouponSelectAll(String userNum);

	public CouponDTO couponSelectOne(String couponNum);

	public void empCouponUpdate(CouponDTO couponDTO);

	public void ownerCouponUpdate(CouponDTO couponDTO);

	public void ownerCouponDelete(String couponNum, String userNum);

	public void empCouponDelete(String couponNum, String userNum);
	
}
