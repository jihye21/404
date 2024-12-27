package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("bookDTO")
public class BookDTO {
	String bookNum;
	String memNum;
	String storeNum;
	String themeNum;
	String themeTime;
	Integer people;
	Integer price;
	String couponNum;
	String bookStatus;
}
