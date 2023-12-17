package nam.nguyen.store.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Cấu hình máy chủ để chuyển tiếp các tin nhắn tới /topic
        config.enableSimpleBroker("/topic");

        // Cấu hình đường dẫn ứng dụng để bắt đầu với /app
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Đăng ký một endpoint WebSocket với đường dẫn /ws-example và cho phép SockJS
        registry.addEndpoint("/ws-example").withSockJS();
    }
}
