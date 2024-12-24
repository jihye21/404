package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.FriendAddRequestDTO;

@Mapper
public interface FriendAddReqMapper {
	public void friendAddRequestInsert(FriendAddRequestDTO friendAddRequestDTO);
	public FriendAddRequestDTO friendSelectOne(String friendReqNum);

}
