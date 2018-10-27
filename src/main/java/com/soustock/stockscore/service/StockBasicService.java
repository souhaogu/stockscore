package com.soustock.stockscore.service;


import com.soustock.stockscore.vo.StockSimpleVo;

/**
 * Created by xuyufei on 2018/4/28.
 *
 */
public interface StockBasicService {


    StockSimpleVo getStockBasicByStockCode(String stockCode) throws Exception;

}
