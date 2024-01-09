package org.example.rediscache.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * PackageName: org.example.rediscache.news.config
 * FileName: RestTemplateConfig
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
