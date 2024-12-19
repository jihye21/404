package _4.map;

import org.springframework.stereotype.Service;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

@Service
public class KakaoMapAPI {
	
	public void execute() {
		try {
	        String API_URL = "https://dapi.kakao.com/v2/directions.json";
	        String API_KEY = "b35992399c2e02f83076852b326605fb"; // 발급받은 API 키

	        // 출발지와 도착지 좌표 (예: 서울역 -> 강남역)
	        double[] start = {126.977969, 37.566535}; // 서울역
	        double[] end = {127.027632, 37.497939};   // 강남역

	        // URL에 파라미터 추가 (출발지, 도착지, 대중교통 모드)
	        String url = String.format("%s?origin=%f,%f&destination=%f,%f&transit_option=1",
	                                   API_URL, start[0], start[1], end[0], end[1]);

	        // HTTP GET 요청
	        CloseableHttpClient client = HttpClients.createDefault();
	        HttpGet get = new HttpGet(url);
	        get.setHeader("Authorization", "KakaoAK " + API_KEY);

	        // 응답 처리
	        HttpResponse response = client.execute(get);
	        String result = EntityUtils.toString(response.getEntity());

	        // 결과 출력
	        System.out.println("경로 정보: " + result);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
