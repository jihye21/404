package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("storeDTO")
public class StoreDTO {
	String storeNum;
	String bussRegistNum;
	String ownerNum;
	String storeName;
	String storeType;
	String storeIntroduction;
	String storeClosedDate;
	String storeOpenTime;
	Date storeOpenDate;
	String storeAddr;
	String storeAddrDetail;
	String storeCrowded;
	String storeProfileImage;
	String storePrifileStoreImage;
	String storeDetailImage;
	String storeDetailStoreImage;
}
