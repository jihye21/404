package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("groupDTO")
public class GroupDTO {
	String memName;
	String groupName;
	
}
