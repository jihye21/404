package _4.map;

import lombok.Data;

@Data
public class MapCommand {
	String LatLon;
	String Location;
	
	String firstLocation;
	String firstLatLon;
	
	String lastLocation;
	
	String start;
	String end;
	
	String nextUrl;
	String keyword;
}
