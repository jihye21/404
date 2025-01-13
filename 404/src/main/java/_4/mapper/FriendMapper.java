package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.FriendAddRequestDTO;
import _4.domain.FriendDTO;

@Mapper
public interface FriendMapper {
	public void friendAddRequestInsert(FriendAddRequestDTO friendAddRequestDTO);
	public void friendAddInsert(FriendDTO friendDTO);
	
	public String selectFriendReqCheck(String friendNickname);
	public String selectFriendListCheck(String friendNickName);
	
	public FriendAddRequestDTO friendReqSelectOne(String friendReqNum);
	public String changeNick(String toNum);
	public List<FriendAddRequestDTO> friendReqSelectAll(String toNum);
	public List<FriendDTO> friendsSelectAll(String memberNum);
	public String changeNum(String friendNum);
	
	public void friendReqDelete(String friendReqNum);
	public void friendDelete(@Param("memberNum") String memberNum, @Param("friendNum") String friendNum);
}
