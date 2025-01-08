package _4.chatting;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import _4.kafka.KafkaWebSocketServer;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatConsumer extends WebSocketServer{
	private Set<WebSocket> connections = ConcurrentHashMap.newKeySet();
	
	public ChatConsumer(InetSocketAddress address) {
        super(address);//주소 초기화
    }
	
	@Override          //연결           //연결 초기 정보
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
        System.out.println("New WebSocket connection: " + conn.getRemoteSocketAddress());
    }
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Closed WebSocket connection: " + conn.getRemoteSocketAddress());
    }
    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from WebSocket client: " + message);
        // 여기에 WebSocket 클라이언트로부터 받은 메시지를 처리하는 로직을 추가할 수 있습니다.
    }
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }
    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully");
    }
    public void broadcastMessage(String message) {
        for (WebSocket conn : connections) {
            conn.send(message);
        }
    }
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("localhost", 8081);
        KafkaWebSocketServer server = new KafkaWebSocketServer(address);
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
        // Kafka Consumer 실행
        server.startKafkaConsumer();
    }
    public void startKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // Kafka 서버 주소
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "websocket-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // Kafka 메시지 수신 및 WebSocket 전송 쓰레드 실행
        new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }
        }).start();
    }
}
