package _4.service.store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.StoreCommand;
import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.AutoNumService;

@Service
public class StoreRegistService {
	
	@Autowired
	StoreMapper storeMapper;
	
	@Autowired
	AutoNumService autoNumService;
	
	public void execute(StoreCommand storeCommand) {
		StoreDTO storeDTO = new StoreDTO();
		
		String storeApplNum = autoNumService.execute("store_application", "store_appl_num ", "store_appl_");
		storeDTO.setStoreApplNum(storeApplNum);
		
		storeDTO.setStoreName(storeCommand.getStoreName());
		
		//사장 이름 session?
		//storeDTO.setOwnerName();
		
		storeDTO.setStoreAddr(storeCommand.getStoreAddr());
		storeDTO.setStoreAddrDetail(storeCommand.getStoreAddrDetail());
		storeDTO.setStoreIntroduction(storeCommand.getStoreIntroduction());
		storeDTO.setStoreOpenDate(storeCommand.getStoreOpenDate());
		
		storeDTO.setBussRegistNum(storeCommand.getBussRegistNum());
		
		//사업자 등록증 이미지
		
		//file 추가
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		
		MultipartFile mf = storeCommand.getBussRegistImage();
		String originalFile = mf.getOriginalFilename();
		
		String extension = originalFile.substring(originalFile.lastIndexOf("."));
		
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension;
		
		File file = new File(fileDir + "/" + storeFileName);
		
		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		storeDTO.setBussRegistImage(originalFile);
		storeDTO.setBussRegistStoreImage(storeFileName);
		
		storeMapper.storeFormInsert(storeDTO);
		
	}
}
