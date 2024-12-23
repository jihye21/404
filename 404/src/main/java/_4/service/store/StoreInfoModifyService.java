package _4.service.store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.StoreCommand;
import _4.domain.StoreDTO;

@Service
public class StoreInfoModifyService {
	public void execute(StoreCommand storeCommand) {
		StoreDTO dto = new StoreDTO();
		dto.setStoreNum(storeCommand.getStoreNum());
		dto.setStoreIntroduction(storeCommand.getStoreIntroduction());
		dto.setStoreClosedDate(storeCommand.getStoreClosedDate());
		dto.setStoreOpenTime(storeCommand.getStoreOpenTime());
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		
		MultipartFile mf = storeCommand.getStoreProfileImage();
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
				
		//dto.setStoreProfileImage(storeCommand.getStoreProfileImage());
		//dto.setStoreDetailImage(storeCommand.getStoreDetailImage());
	}
}
