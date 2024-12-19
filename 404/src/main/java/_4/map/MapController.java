package _4.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map")
public class MapController {
	
	@Autowired
	LocationConvertService locationConvertService;
	
	@GetMapping("mapPage")
	public String mapPage() {
		return "thymeleaf/map/mapPage";
	}
	
	@PostMapping("firstLocation")
	public String firstLocation(MapCommand mapCommand, Model model) {
		mapCommand.setFirstLocation(mapCommand.getLocation());
		
		locationConvertService.execute(mapCommand, model);
		model.addAttribute("mapCommand", mapCommand);
		System.out.println(mapCommand);
		
		mapCommand.setFirstLocation(mapCommand.getLatLon());
		
		return "redirect:mapPage";
	}
	
	
}
