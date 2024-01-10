package org.example.rediscache.news.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NewsService {
    private static final String NAVER_DEFAULT_URL = "https://openapi.naver.com/v1/search/news";
    
    private final OpenApiSecretInfo openApiSecretInfo;
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, NewsDto.Response> redisTemplate;
    
    private HttpHeaders baseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", openApiSecretInfo.getNaverClientId());
        headers.set("X-Naver-Client-Secret", openApiSecretInfo.getNaverClientSecret());
        headers.set("Content-Type", "application/json");
        
        return headers;
    }
    
    //    @Cacheable(value = "stockNews", key = "{#search, #count, #start, #sort}", unless = "#result == null")
    public NewsDto.Response searchStockNews(
            String search,
            int count,
            int start,
            String sort) {
        
        String cacheKey = "stockNews: " + search;
        NewsDto.Response cacheValue = redisTemplate.opsForValue().get(cacheKey);
        
        // 캐시 히트일 경우, 저장된 캐시 데이터 반환
        if (cacheValue != null) {
            log.info("Redis cache existed!");
            return cacheValue;
        }
        
        log.info("Redis cache doesn't existed!");
        
        HttpEntity<String> requestMessage = new HttpEntity<>(baseHeaders());
        
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(NAVER_DEFAULT_URL)
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
        
        NewsDto.Response newsResponse = response.getBody();
        if (newsResponse == null) {
            throw new IllegalArgumentException("Open API Server Error");
        }
        
        // 캐시 미스일 경우, 캐시 데이터 저장
        redisTemplate.opsForValue()
                .set(
                        cacheKey,
                        newsResponse,
                        30,
                        TimeUnit.SECONDS
                );
        
        return newsResponse;
    }
}
