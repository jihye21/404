package _4.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.ThemeCommand;
import _4.mapper.StoreMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.UserNumService;
import _4.service.theme.ThemeDetailService;
import _4.service.theme.ThemeListService;
import _4.service.theme.ThemeModifyService;
import _4.service.theme.ThemeRegistService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("theme")
public class ThemeController {
	@Autowired
	ThemeRegistService themeRegistService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	ThemeListService themeListService;
	@Autowired
	ThemeDetailService themeDetailService;
	@Autowired
	ThemeModifyService themeModifyService;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	ThemeMapper themeMapper;
	
	
	@PostMapping("themeManagePage") // 테마 관리 페이지 이동
	public String themeManagePage(@RequestParam("ownerNum") String ownerNum, Model model) {
		String storeNum = storeMapper.storeSelectOne(ownerNum).getStoreNum();
		themeListService.execute(storeNum, model);
		return "thymeleaf/theme/themeManagePage";
	}
	@GetMapping("themeAdd") // 테마 폼 페이지 이동
	public String menuAdd() {
		return "thymeleaf/theme/themeForm";
	}
	@PostMapping("themeAdd") // 테마 추가
	public void menuAdd(ThemeCommand themeCommand, HttpSession session, HttpServletResponse response) {
		String ownerNum = userNumService.execute(session);
		String storeNum = storeMapper.storeSelectOne(ownerNum).getStoreNum();
		themeRegistService.execute(themeCommand, storeNum);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = "<script language='javascript'>";
			  str += "	opener.location.reload();";
			  str += "	window.self.close();";
			  str += "</script>";
		out.print(str);
		out.close();
	}
	@PostMapping("themeModifyPage") // 테마 수정 페이지 이동
	public String themeModify(String themeNum, Model model) {
		themeDetailService.execute(themeNum, model);
		return "thymeleaf/theme/themeModifyForm";
	}
	@PostMapping("themeUpdate") // 테마 수정
	public String themeUpdate(ThemeCommand themeCommand) {
		themeModifyService.execute(themeCommand);
		return "redirect:/theme/themeManagePage";
	}
	@PostMapping("themeDelete") // 테마 삭제
	public @ResponseBody void themeDelete(@RequestBody @RequestParam("themeNum") String themeNum) {
		themeMapper.themeDelete(themeNum);
		themeMapper.themeTimeDeleteAll(themeNum);
	}
	@PostMapping("themeTimeDelete")
	public @ResponseBody void themeTimeDelete(@RequestParam("themeNum") String themeNum, @RequestParam("themeTime") String themeTime) {
		
		themeMapper.themeTimeDelete(themeNum, themeTime);
	}
}
