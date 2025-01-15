package _4.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.DepositDTO;
import _4.mapper.StoreMapper;


@Service
public class PriceCalcService {
	@Autowired
	StoreMapper storeMapper;
	public Integer execute(String discountedPrice, String storeNum, Model model) {
		Integer depositPrice = null;
		// 가게에 대한 예약금 기준 가져오기
		List<DepositDTO> list = storeMapper.depositSettingSelect(storeNum);

		//예약금 기준 설정이 없을 경우
		if(list == null || list.isEmpty()) {
			depositPrice = Integer.parseInt(discountedPrice);
		}
		else {
			//예약금 기준 설정이 있을 경우 
			//startPrice=0, endPrice=44000, depositRate=10
			// 할인된 가격을 넣어서 예약금 가져오기	
			depositPrice = 0;
			
			for(DepositDTO dto : list) {
				if(Integer.parseInt(dto.getStartPrice()) <= Integer.parseInt(discountedPrice)) {
					if(Integer.parseInt(discountedPrice) <= Integer.parseInt(dto.getEndPrice())) {
						double a = (double)Integer.parseInt(dto.getDepositRate()) / (double)100;
						double b = Integer.parseInt(discountedPrice);
						depositPrice = (int)(b * a);
					}
					else {
						depositPrice = Integer.parseInt(discountedPrice); 
					}
				}
				// 최종 결제 금액이 0 미만일 때 else
			}
		}
		
		return depositPrice;
	}
}
