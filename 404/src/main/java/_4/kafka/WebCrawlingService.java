package _4.kafka;

//웹 크롤링 jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebCrawlingService {
	public String execute() {
		String url = "https://finance.naver.com/item/main.naver?code=005930"; 
        try {
            Document doc = Jsoup.connect(url).get();

            Element closingPriceElement = doc.selectFirst(".rate_info .today .no_today em span");

            if (closingPriceElement != null) {
            	StringBuilder closingPrice = new StringBuilder();
            	
            	doc.select(".rate_info .today .no_today em span").forEach(span -> closingPrice.append(span.text()));
                
                return closingPriceElement.text();
            } else {
                return "종가 정보를 찾을 수 없습니다.";
            }
        } catch (IOException e) {
            return "웹 페이지를 불러오는 데 실패했습니다.";
        }
	}
}
