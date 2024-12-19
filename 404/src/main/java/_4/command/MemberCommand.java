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
	String memId;
	String memPw;
	String memPwCon;
	String memName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memBirthDate;
	// String gender;
	String memPhone;
	String memEmail;
	
}
