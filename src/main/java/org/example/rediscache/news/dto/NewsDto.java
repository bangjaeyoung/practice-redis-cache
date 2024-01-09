package org.example.rediscache.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * PackageName: org.example.rediscache.news.dto
 * FileName: NewsDto
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
public class NewsDto implements Serializable {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response implements Serializable {
        private String lastBuildDate;
        private Integer total;
        private Integer start;
        private Integer display;
        private List<Item> items = null;
        
        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        private static class Item implements Serializable {
            private String title;
            private String originallink;
            private String link;
            private String description;
            private String pubDate;
        }
    }
}
