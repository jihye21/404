package _4.service.store;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.StoreCommand;
import _4.domain.FileDTO;
import _4.domain.StoreDTO;
import _4.mapper.StoreMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class StoreInfoModifyService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(StoreCommand storeCommand, HttpSession session) {
		StoreDTO dto = new StoreDTO();
		dto.setStoreNum(storeCommand.getStoreNum());
		dto.setStoreIntroduction(storeCommand.getStoreIntroduction());
		dto.setStoreClosedDate(storeCommand.getStoreClosedDate());
		dto.setStoreOpenTime(storeCommand.getStoreOpenTime());
		dto.setStoreAddrDetail(storeCommand.getStoreAddrDetail());
		
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
		dto.setStoreProfileImage(originalFile);
		dto.setStoreProfileStoreImage(storeFileName);
		
		String originalTotal = "";
		String storeTotal = "";
		if(!storeCommand.getStoreDetailImage()[0].getOriginalFilename().isEmpty()) {
			for(MultipartFile mpf : storeCommand.getStoreDetailImage()) {
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
			dto.setStoreDetailImage(originalTotal);
			dto.setStoreDetailStoreImage(storeTotal);
		}
		
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("storeList");
		StoreDTO storeDTO = storeMapper.storeSelectOne(storeCommand.getStoreNum());
		if(storeDTO.getStoreDetailImage() != null) {
			List<String> dbOrg = new ArrayList<>(Arrays.asList(storeDTO.getStoreDetailImage().split("[/`]")));
			List<String> dbStore = new ArrayList<>(Arrays.asList(storeDTO.getStoreDetailStoreImage().split("[/`]")));
			if (list != null) {
				for (FileDTO fdto : list) {
					for (String img : dbOrg) {
						if (fdto.getOrgFile().equals(img)) {
							dbOrg.remove(fdto.getOrgFile());
							dbStore.remove(fdto.getStoreFile());
							break;
						}
					}
				}
			}
			for (String img : dbOrg)
				originalTotal += img + "/";
			for (String img : dbStore)
				storeTotal += img + "/";
		}
		dto.setStoreDetailStoreImage(storeTotal);
		dto.setStoreDetailImage(originalTotal);
		
		int i = storeMapper.storeUpdate(dto);
		if (i > 0) {
			if (list != null) {
				for (FileDTO fd : list) {
					File file1 = new File(fileDir + "/" + fd.getStoreFile());
					if(file1.exists())
						file1.delete();
				}
				
			}
		}
	}
}
