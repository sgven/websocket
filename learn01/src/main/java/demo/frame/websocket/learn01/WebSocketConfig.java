package demo.frame.websocket.learn01;

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
        //启用了一个前缀是 /topic (基于内存)的simpleBroker
        config.enableSimpleBroker("/topic");
        //设置了应用消息的前缀，使用@MessageMapping注解的方法，都会加上这个前缀
        //比如，GreetingController.greeting()方法，它的端点endpoint 就是/app/hello
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册了一个 /gs-guide-websocket 端点endpoint,启用SockJS后备选项，以便在WebSocket不可用时可以使用备用传输。
        //SockJS客户端将尝试连接/gs-guide-websocket并使用最佳的可用传输方式
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
