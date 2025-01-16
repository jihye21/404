package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.WaitNumCommand;
import _4.domain.WaitNumDTO;
import _4.mapper.StoreMapper;

@Service
public class WaitNumInsertService {
	@Autowired
	WaitNumService waitNumService;
	@Autowired
	StoreMapper storeMapper;
	public void execute(WaitNumCommand waitNumCommand) {
		Integer waitNum = waitNumService.execute(waitNumCommand.getStoreNum());
		WaitNumDTO dto = new WaitNumDTO();
		dto.setStoreNum(waitNumCommand.getStoreNum());
		dto.setBookNum(waitNumCommand.getBookNum());
		dto.setWaitNum(waitNum);
		storeMapper.waitNumInsert(dto);
	}
}
