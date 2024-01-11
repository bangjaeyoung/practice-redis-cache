package org.example.rediscache.stock.overall.repository;

import org.example.rediscache.stock.overall.entity.KOSPIStockList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * PackageName: org.example.rediscache.stock.overall.repository
 * FileName: KOSPIStockListRepository
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
public interface KOSPIStockListRepository extends JpaRepository<KOSPIStockList, Long> {
    
    // TODO Refactoring : 성능 개선을 위한 Querydsl의 exists으로 변경 필요
    @Query("SELECT COUNT(s.id) > 0 " +
            "FROM KOSPI_STOCK_LIST s " +
            "WHERE s.basDt =:basDt")
    boolean exists(@Param("basDt") String basDt);
}
