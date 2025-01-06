package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("groupDTO")
public class GroupDTO {
	String groupNum;
	String groupName;
	
	String memNum;
	
	//이름으로 그룹 초대하기
	String memName;
	
	//값 받아올 때 사용
	String friendNum;
}
