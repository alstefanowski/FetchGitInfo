package com.example.atiper_github_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${github.token}")
    private String token;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        if (!token.isBlank()) {
            ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
                request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                return execution.execute(request, body);
            };
            restTemplate.getInterceptors().add(authInterceptor);
        }

        return restTemplate;
    }
}
