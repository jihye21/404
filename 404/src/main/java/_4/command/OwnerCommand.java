package _4.command;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class OwnerCommand {
	String ownerId;
	String ownerPw;
	String ownerName;
	Date ownerBirth;
	String ownerEmail;
	String ownerPhone;
}
