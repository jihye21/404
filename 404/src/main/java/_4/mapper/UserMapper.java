package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.AuthDTO;

@Mapper
public interface UserMapper {
	public AuthDTO login(String userId);
	public AuthDTO loginSelectOne(String userId);
	public int userCheckUpdate(String email);
	public void save(AuthDTO auth);
}
