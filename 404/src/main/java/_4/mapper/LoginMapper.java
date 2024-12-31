package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	public String selectNickCheck(String memNickname);

	public String selectIdCheck(String memId);

	public String selectFriendListCheck(String friendNickName);

	public String selectFriendReqCheck(String friendNickname);

}
