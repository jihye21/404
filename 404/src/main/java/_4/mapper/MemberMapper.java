package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.MemberDTO;
import _4.domain.StoreDTO;

@Mapper
public interface MemberMapper {
	public void memberRegist(MemberDTO dto);
	public MemberDTO memberSelectOne(String memNum);
	public int memberUpdate(MemberDTO dto);
	public void wishCheck(String userNum, String storeNum);
	public Integer memberPwUpdate(@Param("_newPw") String userPw, @Param("_memId") String userId);
	public String memberNumSelect(String userId);
}
