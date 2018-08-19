package com.soustock.stockscore.vo.score;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyufei on 2016/5/1.
 * 股票的得分列表
 */
public class StockScoreVo implements Serializable {

    /**
     * 综合分
     */
    private double totalScore;

    /**
     * 个股细节分
     */
    private DetailScoreVo stockDetailScoreVo;

    /**
     * 行业细节分
     */
    private DetailScoreVo industryDetailScoreVo;

    public Map<String, DetailScoreVo> getIdeaDetailScoreMap() {
        return ideaDetailScoreMap;
    }

    /**
     * 概念分
     */
    private Map<String, DetailScoreVo> ideaDetailScoreMap = new HashMap<>();

    /**
     * 市场细节分
     */
    private DetailScoreVo marketDetailScoreVo;



    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public DetailScoreVo getStockDetailScoreVo() {
        return stockDetailScoreVo;
    }

    public void setStockDetailScoreVo(DetailScoreVo stockDetailScoreVo) {
        this.stockDetailScoreVo = stockDetailScoreVo;
    }

    public DetailScoreVo getIndustryDetailScoreVo() {
        return industryDetailScoreVo;
    }

    public void setIndustryDetailScoreVo(DetailScoreVo industryDetailScoreVo) {
        this.industryDetailScoreVo = industryDetailScoreVo;
    }

    public DetailScoreVo getMarketDetailScoreVo() {
        return marketDetailScoreVo;
    }

    public void setMarketDetailScoreVo(DetailScoreVo marketDetailScoreVo) {
        this.marketDetailScoreVo = marketDetailScoreVo;
    }

}
