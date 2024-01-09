package org.example.rediscache.news.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.rediscache.news.dto.NewsDto;
import org.example.rediscache.news.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PackageName: org.example.rediscache.news.controller
 * FileName: NewsController
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-news")
public class NewsController {
    
    private final NewsService newsService;
    
    @GetMapping
    public ResponseEntity<NewsDto.Response> getStockNews(
            @RequestParam String search,
            @RequestParam @Min(1) @Max(100) int count,
            @RequestParam @Min(1) @Max(100) int start,
            @RequestParam String sort) {
        
        NewsDto.Response response = newsService.searchStockNews(search, count, start, sort);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
