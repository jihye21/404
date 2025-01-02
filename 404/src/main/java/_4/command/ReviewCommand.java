package _4.command;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewCommand {
	// String storeNum;
	String memNum;
		
	String reviewNum;
	String reviewContents;
	Date reviewDate;
}
