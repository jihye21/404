package _4.map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class OpenRouteService {
	private static final String API_KEY = "5b3ce3597851110001cf62480b834cea2e37414fa8826bf659bec1b2";  // OpenRouteService API 키
    private static final String API_URL = "https://api.openrouteservice.org/v2/directions/driving-car";

    public static void main(String[] args) {
        try {
            // 출발지와 도착지 좌표 (경도, 위도)
            double[] start = {-0.128, 51.5074};  // 예시: 런던
            double[] end = {-0.138, 51.5076};    // 예시: 런던 근처 다른 위치

            // JSON 요청 바디 구성
            JSONObject requestBody = new JSONObject();
            requestBody.put("coordinates", new double[][] {start, end});

            // API 호출
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(API_URL);
            
            // Authorization 헤더와 Content-Type 설정
            post.setHeader("Authorization", API_KEY);
            post.setHeader("Content-Type", "application/json");  // JSON 형식으로 요청
            post.setEntity(new org.apache.http.entity.StringEntity(requestBody.toString()));

            // 요청 실행 및 응답 받기
            org.apache.http.HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            // 경로 계산 결과 출력
            System.out.println("Route: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
