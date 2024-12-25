package _4.command;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendReqDTO")
public class FriendAddRequestCommand {
	String friendReqNum;
	String memNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date friendRegistDate;
}