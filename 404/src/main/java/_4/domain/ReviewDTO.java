package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewDTO")
public class ReviewDTO {
	String reviewNum;
	String bookNum;
	String reviewContents;
	String memNum;
	String storeNum;
	Date reviewDate;
	String reviewAnswerContents;
	String starRate;
}
