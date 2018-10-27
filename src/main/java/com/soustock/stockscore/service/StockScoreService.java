package com.soustock.stockscore.service;


import com.soustock.stockscore.vo.score.StockScoreVo;

/**
 * Created by xuyufei on 2018/4/28.
 */
public interface StockScoreService {


    StockScoreVo queryScore(String stockCode) throws Exception;

}
