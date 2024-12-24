package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userChangePassword")
public class UserChangePasswodDTO {
	String userId;
	String userPhone;
	String userPw;
	
	String tableName;
	String pwColumName;
	String userIdColumName;
	String phoneColumn;
}
