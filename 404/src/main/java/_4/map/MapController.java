package _4.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
@RequestMapping("map")
public class MapController {
	
	@Autowired
	InstaReelService instaReelService;
	
	@Autowired
	InstaLocationService instaLocationService;
	
	@Autowired
	LocationConvertService locationConvertService;
	
	@GetMapping("mapPage")
	public String mapPage() {
		return "thymeleaf/map/mapPage";
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
	@PostMapping("mapPage")
	public String mapPage1() {
		return "thymeleaf/map/mapPage";
	}
	@PostMapping("keywordSearch")
	public String keywordSearch(@RequestParam String keyword, Model model) {
		List<Map<String, String >> reels = new ArrayList<>();
		
		reels = instaReelService.execute(keyword);
		
		model.addAttribute("reels", reels);
		
		return "forward:/map/mapPage";
	}
	@PostMapping("nextMedia")
	public @ResponseBody String nextMedia(@RequestParam ("mediaId") String mediaId) {
		
		System.out.println("MapController: " +  mediaId);
		
		try {
			instaReelService.nextMedia(mediaId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
