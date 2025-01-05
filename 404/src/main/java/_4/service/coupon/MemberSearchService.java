package _4.service.coupon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.MemberDTO;
import _4.mapper.CouponMapper;

@Service
public class MemberSearchService {
	@Autowired
	CouponMapper couponMapper;
	public List<MemberDTO> execute(String memNickname, Model model) {
		MemberDTO memberDTO = new MemberDTO();
		List<MemberDTO> memberSearchList = new ArrayList<MemberDTO>();
		
		if(memNickname != null) {
			memberSearchList = couponMapper.memberSearchSelectAll(memNickname);
			model.addAttribute("memberSearchList", memberSearchList);
		}
		
		return memberSearchList;
	}
}
