package com.soustock.stockscore.service;


import com.soustock.stockscore.vo.score.StockScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by xuyufei on 2018/4/28.
 */
@Service("stockScoreService")
public class StockScoreServiceImpl implements StockScoreService {

    @Autowired
    private SafetyScoreService safetyScoreService;


    @Override
    public StockScoreVo queryLongScore(String stockCode) throws Exception {
        double safetyScore = safetyScoreService.getScore(stockCode);
        StockScoreVo stockScoreVo = new StockScoreVo();
        stockScoreVo.setTotalScore(safetyScore);    //综合评分
        stockScoreVo.setSafetyScore(safetyScore);   //安全边际
        return stockScoreVo;
    }

}
