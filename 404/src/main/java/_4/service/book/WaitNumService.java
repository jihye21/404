package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.mapper.StoreMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class WaitNumService {
	@Autowired
	StoreMapper storeMapper;
	public Integer execute(String storeNum) {
		Integer waitNum = storeMapper.waitNumCheck(storeNum);
		if(waitNum == null) { // 대기번호가 없다면 1번 부여
			waitNum = 1;
		}
		else { // 대기번호가 있다면 가장 마지막 번호 부여
			waitNum += 1;
		}
		return waitNum;
	}
}
