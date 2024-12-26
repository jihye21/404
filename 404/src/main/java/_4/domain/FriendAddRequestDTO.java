package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendAddRequestDTO")
public class FriendAddRequestDTO {
	String friendReqNum;
	String fromNum;
	String toNum;
	String memNickname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date friendRequestDate;
}
