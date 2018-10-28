package com.soustock.stockscore.service;


import com.soustock.stockscore.common.RemoteQuoteCommon;
import com.soustock.stockscore.vo.StockSimpleVo;
import org.springframework.stereotype.Service;

/**
 * Created by xuyufei on 2018/4/28.
 *
 */
@Service("stockBasicService")
public class StockBasicServiceImpl implements StockBasicService {

    @Override
    public StockSimpleVo getStockBasicByStockCode(String stockCode) throws Exception {
        StockSimpleVo stockSimpleVo = RemoteQuoteCommon.getStockSimpleVoByStockCode(stockCode);
        return stockSimpleVo;
    }
}
