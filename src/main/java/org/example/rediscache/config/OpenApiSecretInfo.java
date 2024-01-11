package org.example.rediscache.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * PackageName: org.example.rediscache.config
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
    
    // 네이버 주식 뉴스 API 요청 관련 키
    private String naverClientId;
    private String naverClientSecret;
    
    // 누리집 API 요청 관련 키
    private String serviceKey;
}
