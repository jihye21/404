package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("bookDTO")
public class BookDTO {
	String bookNum;
	String memNum;
	String groupNum;
	String storeNum;
	String themeNum;
	String themeTime;
	Integer people;
	Integer price;
	Integer depositPrice;
	Integer finalPrice;
	String couponNum;
	String bookStatus;
}
