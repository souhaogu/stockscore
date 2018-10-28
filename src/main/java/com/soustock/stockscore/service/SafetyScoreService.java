package com.soustock.stockscore.service;


/**
 * Created by xuyufei on 2018/10/27.
 */
public interface SafetyScoreService {

    double getScore(String stockCode) throws Exception;

}
