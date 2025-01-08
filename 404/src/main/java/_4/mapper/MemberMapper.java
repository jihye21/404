package _4.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.AuthDTO;
import _4.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public int memberRegist(MemberDTO dto);
	public MemberDTO memberSelectOne(String memNum);
	public int memberUpdate(MemberDTO dto);
	public void wishCheck(String userNum, String storeNum);
	public Integer memberPwUpdate(@Param("_newPw") String userPw, @Param("_memId") String userId);
	public String memberNumSelect(String userId);
	public void memberDelete(AuthDTO authDTO);
	public Date lastLoginGetDate(AuthDTO auth);
	public void memberStatusUpadate(String memNum);
}
