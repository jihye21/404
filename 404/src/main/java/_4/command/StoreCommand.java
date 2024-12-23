package _4.command;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StoreCommand {
	String storeNum;
	String storeName;
	String bussRegistNum;
	String ownerNum;
	String storeType;
	String storeIntroduction;
	String storeClosedDate;
	String storeOpenTime;
	Date storeOpenDate;
	String storeAddr;
	String storeAddrDetail;
	String storeCrowded;
	MultipartFile storeProfileImage;
	MultipartFile storeDetailImage;	
}
