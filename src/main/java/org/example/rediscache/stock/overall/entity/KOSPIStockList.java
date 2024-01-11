package org.example.rediscache.stock.overall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PackageName: org.example.rediscache.stock.overall.entity
 * FileName: KOSPIStockList
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "KOSPI_STOCK_LIST")
public class KOSPIStockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String basDt;
    private String srtnCd;
    private String isinCd;
    private String itmsNm;
    private String mrktCtg;
    private String clpr;
    private String vs;
    private String fltRt;
    private String mkp;
    private String hipr;
    private String lopr;
    private String trqu;
    private String trPrc;
    private String lstgStCnt;
    private String mrktTotAmt;
}
