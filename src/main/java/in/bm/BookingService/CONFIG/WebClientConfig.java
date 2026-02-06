package in.bm.BookingService.CONFIG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder builder(){
        return WebClient.builder();
    }
}
