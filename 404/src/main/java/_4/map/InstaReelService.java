package _4.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class InstaReelService {
	private static final String ACCESS_TOKEN = "EAAPs0aERPXgBO50FezGHWnI63KkP14HBAqGnRYqJQcApefOcMYK2AHnFEgLCrYsxmxdHYq5msPtZA03uPAEutRZATgiH2ZCvQLrVkp5biqAhFWZCBm8oyLZB2uK1okJ1UbOORDaZATOvkNqTbtoLFszqj62ZCgSixGKYZCEEQP3lEQxeyrR4bnJZCFYoM9ZCIoHCjVFGUXXkw2oMXqZA3hEV2y9y5UTR2gZD";
    private static final String USER_ID = "17841449857311304";
    private static String nextPageUrl = "";
    
    public List<Map<String, String>> execute(String keyword) {
    	List<Map<String, String>> result = new ArrayList();
    	try {
			keyword = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            String hashtagId = getHashtagId(keyword);
            if (hashtagId != null) {
                result =  fetchHashtagMedia(hashtagId);
            } else {
            	getHashtagId(keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // 해시태그 ID 가져오기
    private static String getHashtagId(String keyword) throws Exception {
        String urlString = String.format(
            "https://graph.facebook.com/v21.0/ig_hashtag_search?user_id=%s&q=%s&access_token=%s",
            USER_ID, keyword, ACCESS_TOKEN
        );

        JSONObject response = sendGetRequest(urlString);
        if (response != null && response.has("data")) {
            JSONArray data = response.getJSONArray("data");
            if (data.length() > 0) {
                return data.getJSONObject(0).getString("id");
            }
        }
        return null;
    }
    
    // 해시태그로 게시물 검색
    static List<Map<String, String>> fetchHashtagMedia(String hashtagId) throws Exception {
        String urlString = String.format(
            "https://graph.facebook.com/v21.0/%s/top_media?user_id=%s&fields=id,media_type,media_url,caption,permalink&limit=20&access_token=%s",
            hashtagId, USER_ID, ACCESS_TOKEN
        );
        
        JSONObject response = sendGetRequest(urlString);
        List<Map<String, String>> result = new ArrayList<>();
        if (response != null && response.has("data")) {
            JSONArray posts = response.getJSONArray("data");
            
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                //String mediaType = post.getString("media_type");
                //String mediaUrl = post.getString("media_url");
                String caption = post.optString("caption", "No caption");
                String mediaId = post.getString("id");
                String permalink = post.optString("permalink", "No permalink");
                
                if (response.has("paging") && response.getJSONObject("paging").has("next")) {
                    nextPageUrl = response.getJSONObject("paging").getString("next");
                } else {
                    nextPageUrl = null; 
                }
                
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mediaId", mediaId);
                //postMap.put("mediaUrl", mediaUrl);
                postMap.put("caption", caption);
                //postMap.put("mediaType", mediaType);
                postMap.put("permalink", permalink);
                postMap.put("nextPageUrl", nextPageUrl);
                result.add(postMap);
                
            }
            
        } else {
            System.out.println("게시물을 가져올 수 없습니다.");
        }
		return result;
        
    }
 // 다음 게시물
    static List<Map<String, String>> nextgMedia(String nextUrl) throws Exception {
    	String urlString = nextUrl;
    	
        JSONObject response = sendGetRequest(urlString);
        List<Map<String, String>> result = new ArrayList<>();
        if (response != null && response.has("data")) {
            JSONArray posts = response.getJSONArray("data");
            
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                //String mediaType = post.getString("media_type");
                //String mediaUrl = post.getString("media_url");
                String caption = post.optString("caption", "No caption");
                String mediaId = post.getString("id");
                String permalink = post.optString("permalink", "No permalink");
                
                if (response.has("paging") && response.getJSONObject("paging").has("next")) {
                    nextPageUrl = response.getJSONObject("paging").getString("next");
                } else {
                    nextPageUrl = null; 
                }
                
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mediaId", mediaId);
                //postMap.put("mediaUrl", mediaUrl);
                postMap.put("caption", caption);
                //postMap.put("mediaType", mediaType);
                postMap.put("permalink", permalink);
                postMap.put("nextPageUrl", nextPageUrl);
                result.add(postMap);
                
            }
        } else {
            System.out.println("게시물을 가져올 수 없습니다.");
        }
		return result;
        
    }
    
    // HTTP GET 요청
    private static JSONObject sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return new JSONObject(response.toString());
        } else {
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String inputLine;
            StringBuilder errorResponse = new StringBuilder();

            while ((inputLine = errorStream.readLine()) != null) {
                errorResponse.append(inputLine);
            }
            errorStream.close();

            return null;
        }
    }

}
