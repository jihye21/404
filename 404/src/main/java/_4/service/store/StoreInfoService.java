package _4.service.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;

@Service
public class StoreInfoService {
	@Autowired
	StoreMapper storeMapper;
	
	public void execute(Model model, String ownerNum) {
		
		StoreDTO storeDTO = new StoreDTO();
		
		storeDTO = storeMapper.storeInfoSelectAll(ownerNum);
		
		model.addAttribute("dto", storeDTO);
	}
}
