package org.example.rediscache.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * PackageName: org.example.rediscache.config
 * FileName: RestTemplateConfig
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                // 원본 요청과 응답을 버퍼링하여 여러 번 읽을 수 있도록 설정
                // 요청 및 응답을 로깅하거나 재사용 가능
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                
                // UTF-8 인코딩 방식 사용
                // 문자열 형식의 HTTP 메시지 처리할 수 있도록 설정
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .build();
    }
}
