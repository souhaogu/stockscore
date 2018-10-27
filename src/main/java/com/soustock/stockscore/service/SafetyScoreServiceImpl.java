package com.soustock.stockscore.service;


import com.soustock.stockscore.common.FuquanKind;
import com.soustock.stockscore.model.SafetyScoreModel;
import com.soustock.stockscore.utils.DateUtity;
import com.soustock.stockscore.vo.DayQuoteVo;
import com.soustock.stockscore.vo.MinuteQuoteVo;
import com.soustock.stockscore.vo.StockSimpleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by xuyufei on 2018/10/27.
 */
@Service("safetyScoreService")
public class SafetyScoreServiceImpl implements SafetyScoreService {

    @Autowired
    private StockBasicService stockBasicService;

    @Autowired
    private DayQuoteService dayQuoteService;

    @Autowired
    private RealtimeQuoteService realtimeQuoteService;

    @Override
    public double getScore(String stockCode) throws Exception {
        StockSimpleVo stockSimpleVo = stockBasicService.getStockBasicByStockCode(stockCode);
        String dateOfSevenYearsBefore = DateUtity.getSameDateOfPreYear(7);
        if (stockSimpleVo.getListDate().compareTo(dateOfSevenYearsBefore) > 0){
            throw new Exception(String.format("股票：%s的上市时间太短，无法打分。"));
        }

        String dateOfOneYearsBefore = DateUtity.getSameDateOfPreYear(1);
        List<DayQuoteVo> dayQuoteServiceList = dayQuoteService.queryQuoteByDate(stockCode, dateOfSevenYearsBefore, dateOfOneYearsBefore, FuquanKind.Front.getCode());
        MinuteQuoteVo minuteQuoteVo = realtimeQuoteService.queryRealtimeQuote(stockCode);
        SafetyScoreModel safetyScoreModel = new SafetyScoreModel();
        return safetyScoreModel.getScore(minuteQuoteVo.getAvgPrice(), dayQuoteServiceList);
    }
}
