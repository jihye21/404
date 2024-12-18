package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public void memberRegist(MemberDTO dto);
	public MemberDTO memberSelectOne(String memNum);
	public int memberUpdate(MemberDTO dto);	
}
