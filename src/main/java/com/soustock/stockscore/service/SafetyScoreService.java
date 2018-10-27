package com.soustock.stockscore.service;


import com.soustock.stockscore.exception.BusinessException;

import java.io.IOException;

/**
 * Created by xuyufei on 2018/10/27.
 */
public interface SafetyScoreService {

    double getScore(String stockCode) throws Exception;

}
