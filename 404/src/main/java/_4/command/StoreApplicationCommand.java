package _4.command;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("storeApplDTO")
public class StoreApplicationCommand {
	String storeAppleNum;
	String ownerNum;
	String storeName;
	String storeAddr;
	String storeAddrDetail;
	String storeIntroduction;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date storeOpenDate;
	MultipartFile bussRegistImage;
	MultipartFile bussRegistStoreImage;
	String bussRegistNum;
	MultipartFile storeProfileImage;
	MultipartFile storeProfileStoreImage;
	MultipartFile storeDetailImage[];
	MultipartFile storeDetailStoreImage[];
}
