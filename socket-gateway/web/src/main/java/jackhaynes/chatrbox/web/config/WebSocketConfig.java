package jackhaynes.chatrbox.web.config;

import jackhaynes.chatrbox.web.endpoints.ServerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    final RestConfig chatConfig;

    public WebSocketConfig(RestConfig chatConfig) {
        this.chatConfig = chatConfig;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/ws");
    }

    @Bean
    public WebSocketHandler chatHandler() {
        return new ServerWebSocketHandler(chatConfig.socketPoolSingleton());
    }
}
