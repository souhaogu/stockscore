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
        StockScoreVo stockScoreVo = new StockScoreVo();

        //综合评分
        stockScoreVo.setTotalScore(80.0);

        //个股分
        DetailScoreVo stockDetailScore = new DetailScoreVo();
        stockDetailScore.setAccountScore(80.0);
        stockDetailScore.setDirectionScore(40.0);
        stockDetailScore.setSafetyScore(40.0);
        stockDetailScore.setVitalityScore(60.0);
        stockDetailScore.setEventScore(90.0);
        stockScoreVo.setStockDetailScoreVo(stockDetailScore);

        //行业分
        DetailScoreVo industryDetailScore = new DetailScoreVo();
        industryDetailScore.setAccountScore(50.0);
        industryDetailScore.setDirectionScore(40.0);
        industryDetailScore.setSafetyScore(60.0);
        industryDetailScore.setVitalityScore(90.0);
        industryDetailScore.setEventScore(80.0);
        stockScoreVo.setIndustryDetailScoreVo(industryDetailScore);

        //市场分
        DetailScoreVo marketDetailScore = new DetailScoreVo();
        marketDetailScore.setAccountScore(70.0);
        marketDetailScore.setDirectionScore(50.0);
        marketDetailScore.setSafetyScore(50.0);
        marketDetailScore.setVitalityScore(40.0);
        marketDetailScore.setEventScore(90.0);
        stockScoreVo.setMarketDetailScoreVo(marketDetailScore);

        //概念分ai
        DetailScoreVo ideaAiDetailScore = new DetailScoreVo();
        ideaAiDetailScore.setAccountScore(70.0);
        ideaAiDetailScore.setDirectionScore(80.0);
        ideaAiDetailScore.setSafetyScore(50.0);
        ideaAiDetailScore.setVitalityScore(50.0);
        ideaAiDetailScore.setEventScore(95.0);
        stockScoreVo.getIdeaDetailScoreMap().put("ai", ideaAiDetailScore);

        return stockScoreVo;
    }
}
