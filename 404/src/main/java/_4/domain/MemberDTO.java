package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("mem")
public class MemberDTO {
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
