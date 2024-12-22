package _4.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.StoreApplicationDTO;
import _4.domain.StoreDTO;
import _4.mapper.MainMapper;
import _4.mapper.StoreApplMapper;

@Service
public class StoreRegistService {
	@Autowired
	StoreApplMapper storeApplMapper;
	@Autowired
	MainMapper mainMapper;
	public void execute(String storeApplNum, String category) {
		StoreApplicationDTO storeApplDTO = storeApplMapper.storeApplSelectOne(storeApplNum);
		StoreDTO storeDTO = new StoreDTO();
		String storeNum = mainMapper.autoNumSelect("store", "store_num", "store_");
		storeDTO.setStoreNum(storeNum);
		storeDTO.setBussRegistNum(storeApplDTO.getBussRegistNum());
		storeDTO.setStoreAddr(storeApplDTO.getStoreAddr());
		storeDTO.setStoreAddrDetail(storeApplDTO.getStoreAddrDetail());
		storeDTO.setOwnerNum(storeApplDTO.getOwnerNum());
		storeDTO.setStoreOpenDate(storeApplDTO.getStoreOpenDate());
		storeDTO.setStoreType(category);
		storeApplMapper.storeApply(storeDTO);
	}
}
