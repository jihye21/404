package _4.map;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InstaLocationService {
	private static final String INSTAGRAM_ACCESS_TOKEN = "";

    //public static String getLocationId(double latitude, double longitude) throws Exception {
	public static String getLocationId(double latitude, double longitude) throws Exception {
    	String encodedToken = URLEncoder.encode(INSTAGRAM_ACCESS_TOKEN, StandardCharsets.UTF_8);

    	//String url = String.format(
    			//"https://graph.instagram.com/v14.0/ig_hashtag_search?user_id={user_id}&q={hashtag_name}&access_token=",
    		    //"https://graph.facebook.com/v17.0/search?type=place&center=%f,%f&distance=500&access_token=%s",
    		    //latitude, longitude, encodedToken);
    	
    	String userId;  
    	String hashtagName = "nature";   
    	String accessToken = "";
    	String url = String.format(
    			"https://graph.instagram.com/"
    	);

    	/*
    	String hashtagId = "YOUR_HASHTAG_ID";  // 실제 해시태그 ID
    	String userId = "YOUR_USER_ID";        // 실제 사용자 ID
    	String accessToken = "YOUR_ACCESS_TOKEN";  // 유효한 액세스 토큰
    	String url = String.format(
    		    "https://graph.instagram.com/v16.0/%s/top_media?user_id=%s&fields=id,media_type,media_url,caption&access_token=%s",
    		    hashtagId, userId, accessToken
    		);
        */
        // HttpClient 객체 생성
        HttpClient client = HttpClient.newHttpClient();
        
        // HttpRequest 생성
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        
        // 요청 실행 후 응답 받기
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // 응답 내용 출력
        System.out.println("Response: " + response.body());

        // 응답 내용 파싱
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());
        JsonNode data = root.path("data").get(0);
        
        if (data.isArray() && data.size() > 0) {
            JsonNode firstPlace = data.get(0);
            System.out.println("Location ID: " + firstPlace.path("id").asText());
        } else {
            System.out.println("No places found or invalid response.");
        }
        
        // location ID 반환
        return data.path("id").asText();
    }
}
