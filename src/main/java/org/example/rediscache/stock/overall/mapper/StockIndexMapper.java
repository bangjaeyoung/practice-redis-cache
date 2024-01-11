package org.example.rediscache.stock.overall.mapper;

import org.example.rediscache.stock.overall.dto.StockIndexResponseDto;
import org.example.rediscache.stock.overall.entity.KOSDAQStockIndex;
import org.example.rediscache.stock.overall.entity.KOSPIStockIndex;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * PackageName: org.example.rediscache.stock.overall.mapper
 * FileName: StockIndexMapper
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
@Mapper(componentModel = "spring")
public interface StockIndexMapper {
    List<StockIndexResponseDto> KOSPIStockIndicesToStockIndexResponseDtos(List<KOSPIStockIndex> kospiStockIndices);
    
    List<StockIndexResponseDto> KOSDAQStockIndicesToStockIndexResponseDtos(List<KOSDAQStockIndex> kosdaqStockIndices);
}
