package com.soustock.stockscore.service;


import com.soustock.stockscore.common.RemoteQuoteCommon;
import com.soustock.stockscore.vo.DayQuoteVo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by xuyufei on 2018/4/28.
 */
@Service("dayQuoteService")
public class DayQuoteServiceImpl implements DayQuoteService {

    public List<DayQuoteVo> queryQuoteByDate(String stockCode, String bgnDate, String endDate, String fuquan) throws Exception{
        return RemoteQuoteCommon.queryQuoteByDate(stockCode, bgnDate, endDate, fuquan);
    }
}
