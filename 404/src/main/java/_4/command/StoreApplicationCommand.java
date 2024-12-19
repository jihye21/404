package _4.command;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class StoreApplicationCommand {
	String storeAppleNum;
	String ownerNum;
	String storeName;
	String storeAddr;
	String storeAddrDetail;
	String storeIntroduction;
	Date storeOpenDate;
	MultipartFile bussRegistImage;
	MultipartFile bussRegistStoreImage;
	String bussRegistNum;
}
