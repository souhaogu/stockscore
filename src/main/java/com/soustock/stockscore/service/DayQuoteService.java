package com.soustock.stockscore.service;


import com.soustock.stockscore.common.FuquanKind;
import com.soustock.stockscore.vo.DayQuoteVo;
import java.util.List;

/**
 * Created by xuyufei on 2018/4/28.
 */
public interface DayQuoteService {

    List<DayQuoteVo> queryQuoteByDate(String stockCode, String bgnDate, String endDate, FuquanKind fuquanKind) throws Exception;

}
