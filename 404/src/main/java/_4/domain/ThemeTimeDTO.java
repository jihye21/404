package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("themeTimeDTO")
public class ThemeTimeDTO {
	String themeNum;
	String themeTime;
}
