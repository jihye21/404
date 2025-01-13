package _4.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.AuthDTO;
import _4.domain.UserChangePasswordDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param("_userName")String userName, @Param("_userPhone")String userPhone);
	public int pwUpdate(UserChangePasswordDTO dto);
	public AuthDTO userEmail(@Param("_userId") String userId, @Param("_userPhone")String userPhone);
	

}
