package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authDTO")
public class AuthDTO {
	String userId;
	String userPw;
	String grade;
}
