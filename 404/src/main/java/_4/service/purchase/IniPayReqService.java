package _4.service.purchase;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import _4.domain.BookDTO;
import _4.mapper.MemberMapper;
@Service
public class IniPayReqService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(BookDTO dto, Model model) {	
		Integer purchasePrice = dto.getDepositPrice();
		String buyeremail = memberMapper.memberSelectOne(dto.getMemNum()).getMemEmail();
		String deliveryName = dto.getMemNum();
		String purchaseName = dto.getStoreNum();
		Integer deliveryPhone = 1234;
		String purchaseNum = dto.getBookNum();
		
		String mid					
		= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey;
		try {
			mKey = SignatureUtil.hash(signKey, "SHA-256");
			model.addAttribute("mKey", mKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= purchaseNum;	// 가맹점 주문번호(가맹점에서 직접 설정)
		String price				= String.valueOf(purchasePrice);								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)
		Map<String, String> signParam = new HashMap<String, String>();
		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);
		String signature;
		try {
			signature = SignatureUtil.makeSignature(signParam);
			model.addAttribute("signature", signature);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		model.addAttribute("mid", mid);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("price", price);
		model.addAttribute("timestamp", timestamp);
		
		
		model.addAttribute("deliveryName", deliveryName);
		model.addAttribute("purchaseName", purchaseName);
		model.addAttribute("deliveryPhone", deliveryPhone);
		model.addAttribute("buyeremail", buyeremail);
		
		//상품명
		String goodsName = dto.getThemeNum();
		model.addAttribute("goodsName", goodsName);
	}
}