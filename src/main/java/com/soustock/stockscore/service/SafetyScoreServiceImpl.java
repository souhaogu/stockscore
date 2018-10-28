package com.soustock.stockscore.service;


import com.soustock.stockscore.common.CacheAdapter;
import com.soustock.stockscore.common.Constants;
import com.soustock.stockscore.common.Fetchable;
import com.soustock.stockscore.common.FuquanKind;
import com.soustock.stockscore.model.SafetyScoreModel;
import com.soustock.stockscore.utils.DateUtity;
import com.soustock.stockscore.vo.DayQuoteVo;
import com.soustock.stockscore.vo.MinuteQuoteVo;
import com.soustock.stockscore.vo.StockSimpleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyufei on 2018/10/27.
 */
@Service("safetyScoreService")
public class SafetyScoreServiceImpl implements SafetyScoreService, Fetchable {

    @Autowired
    private StockBasicService stockBasicService;

    @Autowired
    private DayQuoteService dayQuoteService;

    @Autowired
    private RealtimeQuoteService realtimeQuoteService;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public double getScore(String stockCode) throws Exception {

        CacheAdapter cacheAdapterOfQuoteHis = new CacheAdapter(redisCacheManager.getCache(Constants.CACHE_KEY_QUOTES_HIS), this);

        String[] dataKeys = new String[]{stockCode};
        List<Double> closePriceList = (List<Double>) cacheAdapterOfQuoteHis.fetchDataFromCacheOrNot(dataKeys);

        double dRealtimePrice = getRealtimePrice(stockCode);
        SafetyScoreModel safetyScoreModel = new SafetyScoreModel();
        return safetyScoreModel.getScore(dRealtimePrice, closePriceList);
    }

    private double getRealtimePrice(String stockCode) throws Exception {
        //获取即时行情(后复权）
        MinuteQuoteVo minuteQuoteVo = realtimeQuoteService.queryRealtimeQuote(stockCode, FuquanKind.Behind);
        if (minuteQuoteVo != null){
            return minuteQuoteVo.getAvgPrice();
        } else {
            List<DayQuoteVo> dayQuoteVoList = dayQuoteService.queryQuoteData(stockCode, 1, FuquanKind.Behind);
            return dayQuoteVoList.get(0).getClosePrice();
        }
    }

    @Override
    public Object get(String[] dataKeys) throws Exception {
        String stockCode = dataKeys[0];
        return fetchHistoricalQuotesForSafety(stockCode);
    }

    /**
     * 得到过去n年的收盘价序列
     * @param stockCode
     * @return
     * @throws Exception
     */
    private List<Double> fetchHistoricalQuotesForSafety(String stockCode) throws Exception {
        StockSimpleVo stockSimpleVo = stockBasicService.getStockBasicByStockCode(stockCode);
        String dateOfSevenYearsBefore = DateUtity.getSameDateOfPreYear(7);
        if (stockSimpleVo.getListDate().compareTo(dateOfSevenYearsBefore) > 0) {
            throw new Exception(String.format("股票：%s[%s]打分失败，暂时不支持给上市时间短的股票打分.", stockCode, stockSimpleVo.getStockName()));
        }

        //获取历史行情（后复权）
        String dateOfOneYearsBefore = DateUtity.getSameDateOfPreYear(1);
        List<DayQuoteVo> dayQuoteServiceList = dayQuoteService.queryQuoteByDate(stockCode, dateOfSevenYearsBefore, dateOfOneYearsBefore, FuquanKind.Behind);
        List<Double> retList = new ArrayList<>();
        for (DayQuoteVo dayQuoteVo : dayQuoteServiceList) {
            retList.add(dayQuoteVo.getClosePrice());
        }
        return retList;
    }
}
