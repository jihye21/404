package _4.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mysql.cj.Session;

import _4.domain.AuthDTO;
import _4.domain.StoreDTO;
import _4.mapper.MemberMapper;
import _4.mapper.StoreMapper;
import _4.mapper.service.UserNumService;
import jakarta.servlet.http.HttpSession;

@Service
public class WishListService {
	@Autowired 
	UserNumService userNumService;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StoreMapper storeMapper;
	
	public void execute(HttpSession session, Model model) {
		String memberNum = userNumService.execute(session);
		List<StoreDTO> list = storeMapper.wishSelectList(memberNum);
		model.addAttribute("list" , list);
	}
}
