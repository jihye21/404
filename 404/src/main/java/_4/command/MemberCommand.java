package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memNum;
	String memId;
	String memPw;
	String memPwCon;
	String memName;
	String memAddr;
	String memAddrDetail;
	String memPost;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memBirth;
	String gender;
	String memPhone;
	String memEmail;
	
}
