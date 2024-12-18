package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@Data
public class OwnerCommand {
	@NotNull(message ="아이디를 입력해주세요.")
	String ownerId;
	@NotNull
	String ownerPw;
	@NotNull
	String ownerName;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	Date ownerBirth;
	@NotNull
	String ownerEmail;
	@NotNull
	String ownerPhone;
	
	
}
