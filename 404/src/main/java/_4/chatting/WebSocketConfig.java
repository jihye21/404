package _4.chatting;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 브로커에 대한 설정
        config.enableSimpleBroker("/topic");  // 메시지를 받을 목적지
        config.setApplicationDestinationPrefixes("/app");  // 클라이언트가 보내는 메시지의 목적지
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결을 위한 엔드포인트 설정
        registry.addEndpoint("/ws").withSockJS(); // SockJS는 WebSocket을 지원하지 않는 브라우저에서도 사용할 수 있게 해줌
    }
}
