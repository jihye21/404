package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import _4.mapper.service.FileDelService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CheckRestController {
	@Autowired
	FileDelService fileDelService;
	
	@PostMapping("/file/fileDel")
	public int fileDel(String orgFile, String storeFile, HttpSession session) {
		return fileDelService.execute(orgFile, storeFile, session);
	}
}
