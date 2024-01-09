package org.example.rediscache.news.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * PackageName: org.example.rediscache.news.config
 * FileName: OpenApiSecretInfo
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "open-api")
public class OpenApiSecretInfo {
    
    private String naverClientId;
    private String naverClientSecret;
}
