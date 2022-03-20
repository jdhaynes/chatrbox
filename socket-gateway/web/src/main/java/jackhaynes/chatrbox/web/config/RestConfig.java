package jackhaynes.chatrbox.web.config;

import jackhaynes.chatrbox.web.WebSocketPool;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RestConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public WebSocketPool socketPoolSingleton() {
        return new WebSocketPool();
    }
}
