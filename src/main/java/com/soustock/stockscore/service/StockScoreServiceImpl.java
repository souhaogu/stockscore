package com.soustock.stockscore.service;


import com.soustock.stockscore.vo.score.DetailScoreVo;
import com.soustock.stockscore.vo.score.StockScoreVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyufei on 2018/4/28.
 */
@Service("stockScoreService")
public class StockScoreServiceImpl implements StockScoreService {

    @Override
    public StockScoreVo queryLongScore(String stockCode) throws Exception {
        //先返回一个demo
        StockScoreVo stockScoreVo = new StockScoreVo();
        stockScoreVo.setTotalScore(80.0);
        DetailScoreVo stockDetailScore = new DetailScoreVo();
        stockDetailScore.setAccountScore(80.0);
        stockDetailScore.setDirectionScore(80.0);
        stockDetailScore.setSafetyScore(80.0);
        stockDetailScore.setVitalityScore(80.0);
        stockDetailScore.setEventScore(80.0);
        stockScoreVo.setStockDetailScoreVo(stockDetailScore);
        stockScoreVo.setIndustryDetailScoreVo(stockDetailScore);
        Map<String, DetailScoreVo> detailScoreVoMap = new HashMap<>(1);
        detailScoreVoMap.put("ai", stockDetailScore);
        stockScoreVo.setIdeaDetailScoreMap(detailScoreVoMap);
        stockScoreVo.setMarketDetailScoreVo(stockDetailScore);
        return stockScoreVo;
    }
}
