package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StoreCommand {
	String storeName;
	String storeAddr;
	String storeAddrDetail;
	String storeIntroduction;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date storeOpenDate;
	MultipartFile bussRegistImage;
	String bussRegistNum;
}
