package _4.mapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import _4.domain.FileDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class FileDelService {
	public int execute(String orgFile, String storeFile, HttpSession session) {
		int i = 0;
		FileDTO dto = new FileDTO();
		dto.setOrgFile(orgFile);
		dto.setStoreFile(storeFile);
		Boolean newFile = true;
		
		List<FileDTO> list = (List<FileDTO>)session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileDTO>();
		}
		
		for (FileDTO fd : list) {
			if(fd.getStoreFile().equals(storeFile)) {
				list.remove(fd);
				newFile = false;
				break;
			}
		}
		if(newFile) {
			list.add(dto);
			i = 1;
		}
		session.setAttribute("fileList", list);
		return i;
	}
}
