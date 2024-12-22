package _4.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.StoreApplMapper;

@Service
public class StoreApplDeleteService {
	@Autowired
	StoreApplMapper storeApplMapper;
	public void execute(String storeApplNum) {
		storeApplMapper.storeApplDelete(storeApplNum);
	}
}
