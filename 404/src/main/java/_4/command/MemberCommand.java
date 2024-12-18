package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	String memberId;
	String memberPw;
	String memberPwCon;
	String memberName;
	String memberAddr;
	String memberAddrDetail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String memberPost;
	Date memberBirth;
	String gender;
	String memberPhone;
	String memberEmail;
	
}
