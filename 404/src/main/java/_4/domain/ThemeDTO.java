package _4.domain;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("themeDTO")
public class ThemeDTO {
	String themeNum;
	String themeName;
	String themeIntroduction;
	String themeImage;
	String themeStoreImage;
	Integer themePrice;
	Integer limitPeople;
	String themeTime;
	String storeNum;
}
