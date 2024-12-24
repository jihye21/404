package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class InquireCommand {
	String userNum;
	String inquireNum;
	String inquireSubject;
	String inquireContent;
	String inquireType;
	@DateTimeFormat(pattern = "yyy-MM-dd")
	Date inquireDate;
	String inquireAnswer;
}
