package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("depositDTO")
public class DepositDTO {
	Integer depositNum;
	String storeNum;
	String startPrice;
	String endPrice;
	String depositRate;
}
