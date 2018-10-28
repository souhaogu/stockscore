package com.soustock.stockscore.service;


import com.soustock.stockscore.common.FuquanKind;
import com.soustock.stockscore.vo.MinuteQuoteVo;
import java.util.List;

/**
 * Created by xuyufei on 2018/4/28.
 */
public interface RealtimeQuoteService {

    List<MinuteQuoteVo> queryRealtimeQuotes(String stockCode, int recentlyCount, FuquanKind fuquanKind) throws Exception;

    MinuteQuoteVo queryRealtimeQuote(String stockCode, FuquanKind fuquanKind) throws Exception;
}
