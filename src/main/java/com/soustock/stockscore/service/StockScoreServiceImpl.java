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
    public StockScoreVo queryScore(String stockCode) throws Exception {
        StockScoreVo stockScoreVo = new StockScoreVo();
        stockScoreVo.setSafetyScore(safetyScoreService.getScore(stockCode));   //安全分
        return stockScoreVo;
    }

}
