package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.FriendAddRequestDTO;
import _4.domain.FriendDTO;

@Mapper
public interface FriendMapper {
	public void friendAddRequestInsert(FriendAddRequestDTO friendAddRequestDTO);
	public FriendAddRequestDTO friendReqSelectOne(String friendReqNum);
	public void friendAddInsert(FriendDTO friendDTO);
	public String changeNick(String toNum);
	public List<FriendAddRequestDTO> friendReqSelectAll(String toNum);
	public void friendReqDelete(String friendReqNum);
	public List<FriendDTO> friendsSelectAll(String memNick);
	public String changeNum(String friendNum);
}
