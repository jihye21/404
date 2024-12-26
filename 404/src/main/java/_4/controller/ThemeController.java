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
	
	@GetMapping("themeAdd")
	public String menuAdd() {
		return "thymeleaf/theme/themeForm";
	}
	
	@PostMapping("themeAdd")
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
	
	
	@PostMapping("themeManagePage")
	public String themeManagePage(@RequestParam("ownerNum") String ownerNum, Model model) {
		String storeNum = storeMapper.storeSelectOne(ownerNum).getStoreNum();
		themeListService.execute(storeNum, model);
		return "thymeleaf/store/ownerView/themeManagePage";
	}
	
	@PostMapping("themeModifyPage")
	public String themeModify(String themeNum, Model model) {
		themeDetailService.execute(themeNum, model);
		return "thymeleaf/theme/themeModifyForm";
	}
	
	@PostMapping("themeUpdate")
	public String themeUpdate(ThemeCommand themeCommand) {
		themeModifyService.execute(themeCommand);
		return "redirect:/theme/themeManagePage";
	}
	
	@PostMapping("themeDelete")
	public @ResponseBody void themeDelete(@RequestBody @RequestParam("themeNum") String themeNum) {
		themeMapper.themeDelete(themeNum);
	}

}
