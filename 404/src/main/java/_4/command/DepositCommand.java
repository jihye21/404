package _4.command;

import lombok.Data;

@Data
public class DepositCommand {
	String depositNum;
	String storeNum;
	String startPrice[];
	String endPrice[];
	String depositRate[];
}
