package _4.chatting;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//pom.xml에 apache kafka 추가하고 maven update하기
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.mina.core.buffer.IoBuffer;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.DatagramAcceptor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import _4.chatting.ChattingUDPServer;

public class ChattingUDPServer {
	private static final ChattingUDPServer instance = new ChattingUDPServer();
    private KafkaProducer<String, String> producer;
    
    public static ChattingUDPServer getInstance() {
        return instance;
    }
    private ChattingUDPServer() {
        // Kafka Producer 초기화
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
        //this.producer = null;
    }
    public void startServer() {
        DatagramAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new UDPHandler(producer));
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        try {
            acceptor.bind(new InetSocketAddress(22101));
            System.out.println("MultiThreaded UDP Echo Server is listening on port 22101");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class UDPHandler extends IoHandlerAdapter {
        private final ExecutorService threadPool = Executors.newCachedThreadPool();
        private KafkaProducer<String, String> producer;
        public UDPHandler(KafkaProducer<String, String> producer) {
        	System.out.println("producer: " + producer);
            this.producer = producer;
        }
        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
        	System.out.println("Received message in session: " + session.getId());  // 세션 ID 확인
            if (message instanceof IoBuffer) {
                IoBuffer buffer = (IoBuffer) message;
                System.out.println("Received message with size: " + buffer.remaining());  // 데이터 크기 확인
                
                threadPool.execute(new ClientHandler(session, buffer,producer));
            }
        }
    }
    private static class ClientHandler implements Runnable {
        private IoSession session;
        private IoBuffer buffer;
        private long startTime;
        private long endTime;
        private KafkaProducer<String, String> producer;
        public ClientHandler(IoSession session, IoBuffer message, KafkaProducer<String, String> producer) {
            this.session = session;
            this.buffer = message.duplicate(); // IoBuffer 복제
            this.producer = producer;
        }
        public void run() {
            try {
                long threadCnt = Thread.activeCount();
                long threadId = Thread.currentThread().getId();
                if (buffer.remaining() < 195) {
                    String bufferContent = buffer.getString(buffer.remaining(), StandardCharsets.UTF_8.newDecoder());
                    System.out.println("T_id = " + threadId + " : " + bufferContent);
                    return;
                }
                byte[] data = new byte[buffer.remaining()]; // 남은 데이터 크기만큼 배열 생성
                buffer.get(data); // 버퍼 내용을 배열에 복사
                System.out.println("Buffer contents: " + new String(data, Charset.forName("UTF-8"))); // UTF-8로 변환하여 출력
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
    	ChattingUDPServer.getInstance().startServer();
    }
}
