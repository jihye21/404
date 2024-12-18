package _4.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class OwnerCommand {
	String ownerId;
	String ownerPw;
	String ownerName;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//Date ownerBirth;
	String ownerEmail;
	String ownerPhone;
}
