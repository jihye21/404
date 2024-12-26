package _4.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias("friendDTO")
public class FriendDTO {
	String memNum;
	String friendNum;
	Date friendRegistDate;
	String memNickname;
}
