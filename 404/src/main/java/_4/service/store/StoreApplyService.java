package _4.service.store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.StoreApplicationCommand;
import _4.command.StoreCommand;
import _4.domain.StoreApplicationDTO;
import _4.mapper.StoreApplMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class StoreApplyService {
	
	@Autowired
	StoreApplMapper storeApplMapper;
	
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	
	public void execute(StoreApplicationCommand storeApplicationCommand, HttpSession session, String ownerNum) {
		StoreApplicationDTO storeApplicationDTO = new StoreApplicationDTO();
		String storeApplNum = autoNumService.execute("store_application", "store_appl_num ", "store_appl_");
		storeApplicationDTO.setStoreApplNum(storeApplNum);
		storeApplicationDTO.setStoreName(storeApplicationCommand.getStoreName());
		storeApplicationDTO.setOwnerNum(ownerNum);
		storeApplicationDTO.setStoreAddr(storeApplicationCommand.getStoreAddr());
		storeApplicationDTO.setStoreAddrDetail(storeApplicationCommand.getStoreAddrDetail());
		storeApplicationDTO.setStoreIntroduction(storeApplicationCommand.getStoreIntroduction());
		storeApplicationDTO.setStoreOpenDate(storeApplicationCommand.getStoreOpenDate());
		storeApplicationDTO.setBussRegistNum(storeApplicationCommand.getBussRegistNum());
		
		//사업자 등록증 이미지
		//file 추가
		URL resource = getClass().getClassLoader().getResource("static/upload");
		System.out.println("resource : " + resource);
		String fileDir = resource.getFile();
		
		MultipartFile mf = storeApplicationCommand.getBussRegistImage();
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
		
		//
		resource = getClass().getClassLoader().getResource("static/upload");
		System.out.println("resource : " + resource);
		fileDir = resource.getFile();
		
		mf = storeApplicationCommand.getStoreProfileImage();
		originalFile = mf.getOriginalFilename();
		
		extension = originalFile.substring(originalFile.lastIndexOf("."));
		
		storeName = UUID.randomUUID().toString().replace("-", "");
		storeFileName = storeName + extension;
		
		file = new File(fileDir + "/" + storeFileName);
		
		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		storeApplicationDTO.setStoreProfileImage(originalFile);
		storeApplicationDTO.setStoreProfileStoreImage(storeFileName);
		
		String originalTotal = "";
		String storeTotal = "";
		if(!storeApplicationCommand.getStoreDetailImage()[0].getOriginalFilename().isEmpty()) {
			for(MultipartFile mpf : storeApplicationCommand.getStoreDetailImage()) {
				originalFile = mpf.getOriginalFilename();
				extension = originalFile.substring(originalFile.lastIndexOf("."));
				storeName = UUID.randomUUID().toString().replace("-", "");
				storeFileName = storeName + extension;
				file = new File(fileDir + "/" + storeFileName);
				try {
					mpf.transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
			storeApplicationDTO.setStoreDetailImage(originalTotal);
			storeApplicationDTO.setStoreDetailStoreImage(storeTotal);
		}
		
		
		storeApplMapper.storeApplicationInsert(storeApplicationDTO);
		
	}
}
