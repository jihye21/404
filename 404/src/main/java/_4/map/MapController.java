package _4.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.domain.AuthDTO;
import _4.domain.GroupShareDTO;
import _4.service.group.GroupChattingInfoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("map")
public class MapController {
	@Autowired
	GroupChattingInfoService groupChattingInfoService;
	
	@Autowired
	InstaReelService instaReelService;
	
	@Autowired
	InstaLocationService instaLocationService;
	
	@Autowired
	LocationConvertService locationConvertService;
	
	@GetMapping("mapPage")
	public String mapPage(@RequestParam String groupNum, HttpSession session, Model model) {
		GroupShareDTO groupShareDTO =  groupChattingInfoService.execte(groupNum, session);
		
		if(groupShareDTO == null) {
			return "redirect:/user/loginForm";
		}
		
		model.addAttribute("groupNum", groupNum);
		model.addAttribute("groupShareDTO", groupShareDTO);
		return "thymeleaf/map/mapPage";
	}
	
	@PostMapping("mapPage")
	public String mapPage1() {
		return "thymeleaf/map/mapPage";
	}
	
	@PostMapping("keywordSearch")
	public String keywordSearch(@RequestParam String keyword, Model model) {
		List<Map<String, String >> reels = new ArrayList<>();
		
		keyword = keyword.replaceAll("\\s+", "");
		reels = instaReelService.execute(keyword);
		
		model.addAttribute("reels", reels);
		model.addAttribute("keyword", keyword);
		
		return "forward:/map/mapPage";
	}
	
	@PostMapping("nextPost")
	public String nextPost(MapCommand mapCommand, Model model) {
		List<Map<String, String >> reels = new ArrayList<>();
		String nextUrl = mapCommand.getNextUrl();
		try {
			reels = instaReelService.nextgMedia(nextUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String keyword = mapCommand.getKeyword();
		model.addAttribute("reels", reels);
		model.addAttribute("keyword", keyword);
		return "forward:/map/mapPage";
	}
	
	@RequestMapping("naverMap")
	public String naverMap(MapCommand mapCommand, Model model) {
		model.addAttribute("mapCommand", mapCommand);
		return "thymeleaf/naverMap";
	}
	@PostMapping("searchRoute")
	public String searchRoute(MapCommand mapCommand, Model model) {
		model.addAttribute("mapCommand", mapCommand);
		return "thymeleaf/naverMap";
	}
	@PostMapping("location")
	public String firstLocation(MapCommand mapCommand, Model model) {
		mapCommand.setFirstLocation(mapCommand.getLocation());
		
		//double[] location = locationConvertService.execute(mapCommand, model);
		
		try {
			double[] location = new double[2];
			location[0] = 37.4013542;  // 위도
			location[1] = 126.9222214; // 경도
			
			String locationId = instaLocationService.getLocationId(location[0], location[1]);
			 
			//List<String> reels = InstaReelService.getReels(locationId);
			
			//for (String reel : reels) {
	          //  System.out.println(reel);
	       // }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:mapPage";  
	}
	
}
