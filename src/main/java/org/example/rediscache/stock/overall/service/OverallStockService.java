package org.example.rediscache.stock.overall.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rediscache.exception.BusinessLogicException;
import org.example.rediscache.exception.ExceptionCode;
import org.example.rediscache.stock.overall.entity.KOSDAQStockIndex;
import org.example.rediscache.stock.overall.entity.KOSDAQStockList;
import org.example.rediscache.stock.overall.entity.KOSPIStockIndex;
import org.example.rediscache.stock.overall.entity.KOSPIStockList;
import org.example.rediscache.stock.overall.repository.KOSDAQStockIndexRepository;
import org.example.rediscache.stock.overall.repository.KOSDAQStockListRepository;
import org.example.rediscache.stock.overall.repository.KOSPIStockIndexRepository;
import org.example.rediscache.stock.overall.repository.KOSPIStockListRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * PackageName: org.example.rediscache.stock.overall.service
 * FileName: OverallStockService1
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OverallStockService {
    
    private final KOSPIStockIndexRepository kospiStockIndexRepository;
    private final KOSDAQStockIndexRepository kosdaqStockIndexRepository;
    private final KOSPIStockListRepository kospiStockListRepository;
    private final KOSDAQStockListRepository kosdaqStockListRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    
    public List<KOSPIStockIndex> getKOSPIStockIndex() {
        List<KOSPIStockIndex> foundIndices = kospiStockIndexRepository.findAll();
        verifyExistsData(foundIndices);
        
        String theMostRecentBasDt = foundIndices.get(0).getBasDt();
        List<KOSPIStockIndex> theMostRecentStockIndices = foundIndices.stream().filter(e -> e.getBasDt().equals(theMostRecentBasDt)).collect(Collectors.toList());
        
        String cacheKey = "KOSPIStockIndex: ";
        Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
        
        // 캐시 히트일 경우, 저장된 캐시 데이터 반환
        if (cacheValue != null) {
            log.info("Redis cache existed!");
            return (List<KOSPIStockIndex>) cacheValue;
        }
        
        log.info("Redis cache doesn't existed!");
        
        // 캐시 미스일 경우, 캐시 데이터 저장
        redisTemplate.opsForValue()
                .set(
                        cacheKey,
                        theMostRecentStockIndices,
                        24,
                        TimeUnit.HOURS
                );
        
        
        return theMostRecentStockIndices;
    }
    
    public List<KOSDAQStockIndex> getKOSDAQStockIndex() {
        List<KOSDAQStockIndex> foundIndices = kosdaqStockIndexRepository.findAll();
        verifyExistsData(foundIndices);
        
        String theMostRecentBasDt = foundIndices.get(0).getBasDt();
        List<KOSDAQStockIndex> theMostRecentStockIndices = foundIndices.stream().filter(e -> e.getBasDt().equals(theMostRecentBasDt)).collect(Collectors.toList());
        
        String cacheKey = "KOSDAQStockIndex: ";
        Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
        
        // 캐시 히트일 경우, 저장된 캐시 데이터 반환
        if (cacheValue != null) {
            log.info("Redis cache existed!");
            return (List<KOSDAQStockIndex>) cacheValue;
        }
        
        log.info("Redis cache doesn't existed!");
        
        // 캐시 미스일 경우, 캐시 데이터 저장
        redisTemplate.opsForValue()
                .set(
                        cacheKey,
                        theMostRecentStockIndices,
                        24,
                        TimeUnit.HOURS
                );
        
        return theMostRecentStockIndices;
    }
    
    public List<KOSPIStockList> getKOSPIStockList() {
        List<KOSPIStockList> foundLists = kospiStockListRepository.findAll();
        verifyExistsData(foundLists);
        
        String theMostRecentBasDt = foundLists.get(0).getBasDt();
        List<KOSPIStockList> theMostRecentStockLists = foundLists.stream().filter(e -> e.getBasDt().equals(theMostRecentBasDt)).collect(Collectors.toList());
        
        String cacheKey = "KOSPIStockList: ";
        Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
        
        // 캐시 히트일 경우, 저장된 캐시 데이터 반환
        if (cacheValue != null) {
            log.info("Redis cache existed!");
            return (List<KOSPIStockList>) cacheValue;
        }
        
        log.info("Redis cache doesn't existed!");
        
        // 캐시 미스일 경우, 캐시 데이터 저장
        redisTemplate.opsForValue()
                .set(
                        cacheKey,
                        theMostRecentStockLists,
                        24,
                        TimeUnit.HOURS
                );
        
        return theMostRecentStockLists;
    }
    
    public List<KOSDAQStockList> getKOSDAQStockList() {
        List<KOSDAQStockList> foundLists = kosdaqStockListRepository.findAll();
        verifyExistsData(foundLists);
        
        String theMostRecentBasDt = foundLists.get(0).getBasDt();
        List<KOSDAQStockList> theMostRecentStockLists = foundLists.stream().filter(e -> e.getBasDt().equals(theMostRecentBasDt)).collect(Collectors.toList());
        
        String cacheKey = "KOSDAQStockList: ";
        Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
        
        // 캐시 히트일 경우, 저장된 캐시 데이터 반환
        if (cacheValue != null) {
            log.info("Redis cache existed!");
            return (List<KOSDAQStockList>) cacheValue;
        }
        
        log.info("Redis cache doesn't existed!");
        
        // 캐시 미스일 경우, 캐시 데이터 저장
        redisTemplate.opsForValue()
                .set(
                        cacheKey,
                        theMostRecentStockLists,
                        24,
                        TimeUnit.HOURS
                );
        
        return theMostRecentStockLists;
    }
    
    private void verifyExistsData(List<?> data) {
        if (data.isEmpty()) throw new BusinessLogicException(ExceptionCode.CANNOT_FOUND_STOCK_DATA);
    }
}
