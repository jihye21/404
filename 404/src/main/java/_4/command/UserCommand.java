package _4.command;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCommand {
	@NotBlank(message="아이디를 입력해주세요.")
	String userId;
	@NotBlank(message="비밀번호를 입력해주세요.")
	String userPw;
	String grade;
	LocalDateTime lastLoginTime;
}
