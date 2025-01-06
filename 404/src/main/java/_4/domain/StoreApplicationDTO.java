package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("storeApplicationDTO")
public class StoreApplicationDTO {
	String storeApplNum;
	String storeName;
	String ownerNum;
	String storeAddr;
	String storeAddrDetail;
	String storeIntroduction;
	Date storeOpenDate;
	String bussRegistImage;
	String bussRegistStoreImage;
	String bussRegistNum;
	String storeProfileImage;
	String storeProfileStoreImage;
	String storeDetailImage;
	String storeDetailStoreImage;
}
