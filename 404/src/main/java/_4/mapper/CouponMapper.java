package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.CouponDTO;
import _4.domain.MemberDTO;

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

	public String couponNumSelect(String couponCode);
	
	public void memberCouponInsert(String memNum, String couponNum);

	public List<CouponDTO> memberCouponSelectAll(String memNum);

	public String memberCouponRegistCheck(String memNum, String couponNum);

	public List<MemberDTO> memberSearchSelectAll(String memNickname);

	public void memberCouponSend(String memNum, String couponNum);
	
}
