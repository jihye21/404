package _4.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ThemeCommand {
	String themeNum;
	String themeName;
	String themeIntroduction;
	MultipartFile themeImage;
	Integer themePrice;
	Integer limitPeople;
	String themeTime;
	String storeNum;
}
