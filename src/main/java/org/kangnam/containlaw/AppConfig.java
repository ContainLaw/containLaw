package org.kangnam.containlaw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // ChatGPTConfig와 같은 이름의 빈을 사용하지 않도록 수정
    @Bean(name = "appRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}