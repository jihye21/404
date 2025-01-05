package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.GroupDTO;

@Mapper
public interface GroupMapper {
	
	public List<GroupDTO> groupMemberSearch(String memName, String memNum);
	
}
