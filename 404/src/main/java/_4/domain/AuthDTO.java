package _4.domain;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authDTO")
public class AuthDTO {
	String userId;
	String userPw;
	String grade;
	String userEmail;
	String userName;
	LocalDateTime lastLoginTime;
}
