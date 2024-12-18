package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("memberDTO")
public class MemberDTO {
	String memNum;
	String memId;
	String memPw;
	String memPwCon;
	String memName;
	String memAddr;
	String memAddrDetail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String memPost;
	Date memBirth;
	String gender;
	String memPhone;
	String memEmail;
}
