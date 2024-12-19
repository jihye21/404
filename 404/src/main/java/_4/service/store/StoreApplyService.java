package _4.service.store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.StoreCommand;
import _4.domain.StoreApplicationDTO;
import _4.mapper.StoreMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class StoreApplyService {
	
	@Autowired
	StoreMapper storeMapper;
	
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	
	public void execute(StoreCommand storeCommand, HttpSession session, String ownerNum) {
		StoreApplicationDTO storeApplicationDTO = new StoreApplicationDTO();
		String storeApplNum = autoNumService.execute("store_application", "store_appl_num ", "store_appl_");
		storeApplicationDTO.setStoreApplNum(storeApplNum);
		storeApplicationDTO.setStoreName(storeCommand.getStoreName());
		storeApplicationDTO.setOwnerNum(ownerNum);
		storeApplicationDTO.setStoreAddr(storeCommand.getStoreAddr());
		storeApplicationDTO.setStoreAddrDetail(storeCommand.getStoreAddrDetail());
		storeApplicationDTO.setStoreIntroduction(storeCommand.getStoreIntroduction());
		storeApplicationDTO.setStoreOpenDate(storeCommand.getStoreOpenDate());
		
		storeApplicationDTO.setBussRegistNum(storeCommand.getBussRegistNum());
		
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
		
		storeApplicationDTO.setBussRegistImage(originalFile);
		storeApplicationDTO.setBussRegistStoreImage(storeFileName);
		
		storeMapper.storeFormInsert(storeApplicationDTO);
		
	}
}
