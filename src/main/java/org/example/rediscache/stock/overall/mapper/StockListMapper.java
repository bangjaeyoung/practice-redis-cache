package org.example.rediscache.stock.overall.mapper;


import org.example.rediscache.stock.overall.dto.StockListResponseDto;
import org.example.rediscache.stock.overall.entity.KOSDAQStockList;
import org.example.rediscache.stock.overall.entity.KOSPIStockList;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * PackageName: org.example.rediscache.stock.overall.mapper
 * FileName: StockListMapper
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
@Mapper(componentModel = "spring")
public interface StockListMapper {
    List<StockListResponseDto> KOSPIStockListsToStockListsResponseDtos(List<KOSPIStockList> kospiStockLists);
    
    List<StockListResponseDto> KOSDAQStockListsToStockListsResponseDtos(List<KOSDAQStockList> kosdaqStockLists);
}
