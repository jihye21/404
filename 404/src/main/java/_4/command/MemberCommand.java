package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class MemberCommand {
	String memNum;
	
	@NotEmpty(message = "아이디를 입력해주세요")
	String memId;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String memPwCon;
	
	@NotEmpty(message = "이름을 입력해주세요")
	String memName;
	
	@NotNull(message = "생년월일을 입력해주세요.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memBirth;
	
	String gender;
	
	@NotBlank(message = "연락처을 입력하여 주세요.")
	@Size(min = 11, max = 23)
	String memPhone;
	
	@NotBlank(message = "이메일을 입력하여 주세요.")
	String memEmail;
	
}
