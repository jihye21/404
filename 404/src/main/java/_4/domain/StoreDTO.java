package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("storeDTO")
public class StoreDTO {
	String storeApplNum;
	String storeName;
	
	//session으로 추가?
	String ownerName;
	
	String storeAddr;
	String storeAddrDetail;
	String storeIntroduction;
	Date storeOpenDate;
	
	String bussRegistImage;
	String bussRegistStoreImage;
	
	String bussRegistNum;
}
