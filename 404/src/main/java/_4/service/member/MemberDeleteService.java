package _4.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.AuthDTO;
import _4.mapper.MemberMapper;

@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(AuthDTO authDTO) {
		memberMapper.memberDelete(authDTO);
	}
}
