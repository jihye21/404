package _4.command;

import java.util.List;

import lombok.Data;

@Data
public class BookCommand {
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
	Integer dutchPrice;
	String couponNum;
	String bookStatus;
	
	List<String> dutchMember;
	Integer myDutchPrice;
	
}

