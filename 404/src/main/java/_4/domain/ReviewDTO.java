package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewDTO")
public class ReviewDTO {
	// String storeNum;
	String memNum;
	
	String reviewNum;
	String reviewContents;
	Date reviewDate;
}
