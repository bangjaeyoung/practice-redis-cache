package org.example.rediscache.stock.overall.dto;

import lombok.Data;

/**
 * PackageName: org.example.rediscache.stock.overall.dto
 * FileName: StockListResponseDto1
 * Author: bangjaeyoung
 * Date: 2024-01-11
 * Description:
 */
@Data
public class StockListResponseDto {
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
