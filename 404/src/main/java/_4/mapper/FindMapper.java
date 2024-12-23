package _4.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.UserChangePasswodDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param("_userName")String userName, @Param("_userPhone")String userPhone);
	public int pwUpdate(UserChangePasswodDTO dto);
	

}
