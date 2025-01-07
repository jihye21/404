package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _4.mapper.service.FileDelService;
import _4.service.user.UserEmailCheckService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CheckRestController {
	@Autowired
	FileDelService fileDelService;
	@Autowired
	UserEmailCheckService userEmailCheckService;
	
	@PostMapping("/file/fileDel")
	public int fileDel(String orgFile, String storeFile, HttpSession session) {
		return fileDelService.execute(orgFile, storeFile, session);
	}
	
	@RequestMapping("/userComfirm")
	public String userConfirm(@RequestParam(value="chk") String chk) {
		int i = userEmailCheckService.execute(chk);
		if(i == 0)
			return "이미 인증되었습니다.";
		else return "인증되었습니다.";
	}
}
