package _4.map;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LocationConvertService {

	public double[] execute(MapCommand mapCommand, Model model) {
		
		double longitude = 0; //경도
		double latitude = 0; //위도
		
		try {
            // 주소 입력 (예시: 서울특별시 강남구)
            String address = mapCommand.getLocation();
            
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            
            // Nominatim API URL (주소를 좌표로 변환)
            String apiUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedAddress;

            // HTTP 요청 보내기
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(apiUrl);
            request.setHeader("User-Agent", "YourAppName");

            // 응답 받기
            String response = EntityUtils.toString(client.execute(request).getEntity());

            // JSON 파싱
            org.json.JSONArray jsonResponse = new org.json.JSONArray(response);

            // 좌표 추출 (첫 번째 결과)
            if (jsonResponse.length() > 0) {
                org.json.JSONObject firstResult = jsonResponse.getJSONObject(0);
                longitude = firstResult.getDouble("lon"); // 경도
                latitude = firstResult.getDouble("lat"); // 위도
                
                // 좌표 출력
                System.out.println("Longitude: " + longitude);
                System.out.println("Latitude: " + latitude);
                
            } else {
                System.out.println("주소를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return new double[]{latitude, longitude};
    }
}
