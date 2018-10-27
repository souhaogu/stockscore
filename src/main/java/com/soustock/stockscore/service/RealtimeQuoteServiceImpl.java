package com.soustock.stockscore.service;

import com.soustock.stockscore.common.RemoteQuoteCommon;
import com.soustock.stockscore.utils.ListUtity;
import com.soustock.stockscore.vo.MinuteQuoteVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuyufei on 2018/10/27.
 */
@Service("realtimeQuoteService")
public class RealtimeQuoteServiceImpl implements RealtimeQuoteService {
    @Override
    public List<MinuteQuoteVo> queryRealtimeQuotes(String stockCode, int recentlyCount) throws Exception {
        return RemoteQuoteCommon.queryRealtimeQuotes(stockCode, recentlyCount);
    }

    @Override
    public MinuteQuoteVo queryRealtimeQuote(String stockCode) throws Exception {
        List<MinuteQuoteVo> minuteQuoteVoList = queryRealtimeQuotes(stockCode, 1);
        if (!ListUtity.isNullOrEmpty(minuteQuoteVoList)){
            return minuteQuoteVoList.get(0);
        }
        return null;
    }
}
