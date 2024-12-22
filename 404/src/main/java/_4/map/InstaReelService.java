package _4.map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstaReelService {
	private static final String INSTAGRAM_ACCESS_TOKEN = "";

    public static List<String> getReels(String locationId) throws Exception {
    	String encodedToken = URLEncoder.encode(INSTAGRAM_ACCESS_TOKEN, StandardCharsets.UTF_8);

        String url = String.format(
            "https://graph.facebook.com/v21.0/%s/media?fields=id,media_type,media_url,caption&access_token=%s",
            locationId, encodedToken);

        // http Client 시작
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            // 요청 실행하기
            HttpResponse response = httpClient.execute(request);

            // 응답 string으로 추출하기
            String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Response: " + responseContent);

            // 파싱하기
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseContent);
            JsonNode data = root.path("data");

            // video Type만 추출하기
            List<String> reels = new ArrayList<>();
            for (JsonNode item : data) {
                if ("VIDEO".equals(item.path("media_type").asText())) {
                    reels.add(item.path("media_url").asText());
                }
            }

            return reels;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to fetch Instagram Reels");
        }
    }

    public static void main(String[] args) {
        try {
            List<String> reels = getReels("YOUR_LOCATION_ID");
            System.out.println("Reels: " + reels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
