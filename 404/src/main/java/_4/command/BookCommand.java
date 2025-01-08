package _4.command;

import lombok.Data;

@Data
public class BookCommand {
	String bookNum;
	String memNum;
	String storeNum;
	String themeNum;
	String themeTime;
	Integer people;
	Integer price;
	Integer depositPrice;
	Integer finalPrice;
	String couponNum;
	String bookStatus;
	
	String groupNum;
}

