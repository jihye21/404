package _4.domain;

import java.time.LocalDateTime;
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
	String memName;
	String memNickname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memBirthDate;
	String gender;
	String memPhone;
	String memEmail;
	String status;
	Integer memPoint;
}
