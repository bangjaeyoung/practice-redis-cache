package org.example.rediscache.news.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.rediscache.news.config.OpenApiSecretInfo;
import org.example.rediscache.news.dto.NewsDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * PackageName: org.example.rediscache.news.service
 * FileName: NewsService
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Service
@Transactional
@RequiredArgsConstructor
public class NewsService {
    private static final String NAVER_DEFAULT_URL = "https://openapi.naver.com/v1/search/";
    
    private final OpenApiSecretInfo openApiSecretInfo;
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    
    private HttpHeaders baseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", openApiSecretInfo.getNaverClientId());
        headers.set("X-Naver-Client-Secret", openApiSecretInfo.getNaverClientSecret());
        
        return headers;
    }
    
    //    @Cacheable(value = "stockNews", key = "{#search, #count, #start, #sort}", unless = "#result == null")
    public NewsDto.Response searchStockNews(
            String search,
            int count,
            int start,
            String sort) {
        
        String cacheKey = "stockNews: " + search;
        Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
        if (cacheValue != null) {
            return (NewsDto.Response) cacheValue;
        }
        
        HttpHeaders requestHeaders = baseHeaders();
        requestHeaders.set("Content-Type", "application/json");
        
        HttpEntity<String> requestMessage = new HttpEntity<>(requestHeaders);
        String url = NAVER_DEFAULT_URL + "news";
        
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", search)
                .queryParam("display", count)
                .queryParam("start", start)
                .queryParam("sort", sort)
                .build();
        
        ResponseEntity<NewsDto.Response> response = restTemplate.exchange(
                uriBuilder.toString(),
                HttpMethod.GET,
                requestMessage,
                NewsDto.Response.class
        );
        
        
        redisTemplate.opsForValue()
                .set(
                        String.valueOf(cacheKey),
                        response.getBody(),
                        15,
                        TimeUnit.SECONDS
                );
        
        return response.getBody();
    }
}
