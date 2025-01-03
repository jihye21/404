package _4.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class InstaReelService {
	private static final String ACCESS_TOKEN = "EAAPs0aERPXgBO9elym2xq18ijwFnLr9YYe7B8V3lT2FNvfUcCFEx7YcYjZAuuGZCiSyOoIrP0pZBpZA8xunCf8H8FgR76x0Y4Sx8WmPr0N9MD6KSl65yRCZBXtgZA8yPzEtOonsWXCgCNa3bVarp8LyZCR3pUsBFKSltthCCJ8Bjt0chnAKrPAu2bqxgFZA3H1nHDgtSatLEm38dTyuEstEE7c3OeN0ZD";
    private static final String USER_ID = "17841449857311304";

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
        System.out.println("execute: " + result);
        return result;
    }
    
    //다음 게시물 가져오기
    public static List<Map<String, String>> nextMedia(String mediaId) throws Exception {
    	String urlString = String.format(
    			"https://graph.facebook.com/v21.0/%s/posts?fields=id,message,created_time&access_token=%s"
    			, mediaId, ACCESS_TOKEN);
    	//{page-id}/posts?fields=id,message,created_time&access_token={access-token}
    	
    	JSONObject response = sendGetRequest(urlString);
        List<Map<String, String>> result = new ArrayList<>();
        if (response != null && response.has("data")) {
            JSONArray posts = response.getJSONArray("data");
            System.out.println("posts: " + posts);
            
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                String mediaType = post.getString("media_type");
                String mediaUrl = post.getString("media_url");
                String caption = post.optString("caption", "No caption");
                mediaId = post.getString("id");
                
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mediaId", mediaId);
                postMap.put("mediaUrl", mediaUrl);
                postMap.put("caption", caption);
                postMap.put("mediaType", mediaType);
                result.add(postMap);
                
            }
            
        } else {
            System.out.println("게시물을 가져올 수 없습니다.");
        }
        System.out.println("nextMedia: " + result);
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
            System.out.println("data : " + data);
            if (data.length() > 0) {
            	System.out.println("id: " + data.getJSONObject(0).getString("id"));
                return data.getJSONObject(0).getString("id");
            }
        }
        return null;
    }

    // 해시태그로 게시물 검색
    private static List<Map<String, String>> fetchHashtagMedia(String hashtagId) throws Exception {
        String urlString = String.format(
            "https://graph.facebook.com/v21.0/%s/top_media?user_id=%s&fields=id,media_type,media_url,caption&access_token=%s",
            hashtagId, USER_ID, ACCESS_TOKEN
        );

        JSONObject response = sendGetRequest(urlString);
        List<Map<String, String>> result = new ArrayList<>();
        if (response != null && response.has("data")) {
            JSONArray posts = response.getJSONArray("data");
            System.out.println("posts: " + posts);
            
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                String mediaType = post.getString("media_type");
                String mediaUrl = post.getString("media_url");
                String caption = post.optString("caption", "No caption");
                String mediaId = post.getString("id");
                
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mediaId", mediaId);
                postMap.put("mediaUrl", mediaUrl);
                postMap.put("caption", caption);
                postMap.put("mediaType", mediaType);
                result.add(postMap);
                
            }
            
        } else {
            System.out.println("게시물을 가져올 수 없습니다.");
        }
        System.out.println("fetchHashtag: " + result);
		return result;
        
    }

    // HTTP GET 요청
    private static JSONObject sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("connection: " + responseCode);
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
            System.out.println("HTTP 요청 실패: " + responseCode);
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String inputLine;
            StringBuilder errorResponse = new StringBuilder();

            while ((inputLine = errorStream.readLine()) != null) {
                errorResponse.append(inputLine);
            }
            errorStream.close();

            System.out.println("Error Response: " + errorResponse.toString());
            return null;
        }
    }

}
