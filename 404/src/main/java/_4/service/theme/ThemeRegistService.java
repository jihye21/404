package _4.service.theme;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import _4.command.ThemeCommand;
import _4.domain.ThemeDTO;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;

@Service
public class ThemeRegistService {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	ThemeMapper themeMapper;
	
	public void execute(ThemeCommand themeCommand, String storeNum) {
		ThemeDTO dto = new ThemeDTO();
		String themeNum = autoNumService.execute("theme", "theme_num", "theme_");
		dto.setThemeNum(themeNum);
		dto.setThemeName(themeCommand.getThemeName());
		dto.setThemeIntroduction(themeCommand.getThemeIntroduction());
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		MultipartFile mf = themeCommand.getThemeImage();
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
		dto.setThemeImage(originalFile);
		dto.setThemeStoreImage(storeFileName);
		dto.setThemePrice(themeCommand.getThemePrice());
		dto.setLimitPeople(themeCommand.getLimitPeople());
		dto.setThemeTime(themeCommand.getThemeTime());
		dto.setStoreNum(storeNum);
		themeMapper.themeInsert(dto);
		
	}
}
