package _4.command;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewCommand {
	String reviewNum;
	String bookNum;
	String reviewContents;
	String memNum;
	String storeNum;
	Date reviewDate;
	String reviewAnwserContents;
}
