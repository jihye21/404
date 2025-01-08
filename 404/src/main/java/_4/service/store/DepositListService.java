package _4.service.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import _4.domain.DepositDTO;
import _4.mapper.StoreMapper;

@Service
public class DepositListService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(String storeNum, Model model) {
		List<DepositDTO> list = storeMapper.depositSettingSelect(storeNum);
		model.addAttribute("depositList", list);
	}
}
