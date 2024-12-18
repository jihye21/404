package _4.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ownerDTO")
public class OwnerDTO {
	String ownerId;
	String ownerPw;
	String ownerName;
	Date ownerBirth;
	String ownerEmail;
	String ownerPhone;
	
}
