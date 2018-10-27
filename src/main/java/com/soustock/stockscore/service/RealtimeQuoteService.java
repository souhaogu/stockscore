package com.soustock.stockscore.service;


import com.soustock.stockscore.vo.MinuteQuoteVo;

import java.util.List;

/**
 * Created by xuyufei on 2018/4/28.
 */
public interface RealtimeQuoteService {

    List<MinuteQuoteVo> queryRealtimeQuotes(String stockCode, int recentlyCount) throws Exception;

    MinuteQuoteVo queryRealtimeQuote(String stockCode) throws Exception;
}
