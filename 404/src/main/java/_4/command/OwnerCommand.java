package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Component
@Data
public class OwnerCommand {
	@NotBlank(message="아이디를 입력해주세요.")
	String ownerId;
	@NotBlank(message="비밀번호를 입력해주세요.")
	String ownerPw;
	@NotBlank(message="이름을 작성해주세요.")
	String ownerName;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	Date ownerBirth;
	@NotBlank(message="이메일을 입력해주세요.")
	String ownerEmail;
	@NotBlank(message="전화번호를 입력해주세요.")
	String ownerPhone;
	
	
}
