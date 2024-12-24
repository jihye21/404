package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquireDTO")
public class InquireDTO {
	String inquireNum;
	String memNum;
	String ownerNum;
	String inquireSubject;
	String inquireContent;
	String memberInquireType;
	String ownerInquireType;
	String empNum;
	String inquireAnswer;
	Date inquireDate;
	Date inquireAnswerDate;
}
