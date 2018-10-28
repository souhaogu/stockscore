package com.soustock.stockscore.common;


import com.soustock.stockscore.utils.HttpRequester;
import com.soustock.stockscore.vo.DayQuoteVo;
import com.soustock.stockscore.vo.MinuteQuoteVo;
import com.soustock.stockscore.vo.StockSimpleVo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuyufei on 2018/10/25.
 */
public class RemoteQuoteCommon {

    private final static String BASE_URL_QUOTE = "https://quote.souhaogu.cn/stock_quote";

    /**
     * 获取某个股票的基本信息
     * @return
     * @throws IOException
     */
    public static StockSimpleVo getStockSimpleVoByStockCode(String stockCode) throws Exception {
        String urlString = BASE_URL_QUOTE + "/stockBasic/getStockBasicByStockCode.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        String retStr = httpRequester.sendGet(urlString, params);
        return ReturnMapHandler.handleObject(retStr, "result", StockSimpleVo.class);
    }


    /**
     * 得到某个股票的起止日期的日行情数据
     * @return
     * @throws IOException
     */
    public static List<DayQuoteVo> queryQuoteByDate(String stockCode, String bgnDate, String endDate, FuquanKind fuquanKind) throws Exception {
        String urlString = BASE_URL_QUOTE + "/dayQuote/queryQuoteByDate.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        params.put("bgnDate", bgnDate);
        params.put("endDate", endDate);
        params.put("fuquan", fuquanKind.getCode());
        String retStr = httpRequester.sendGet(urlString, params);
        return ReturnMapHandler.handleList(retStr, "list", DayQuoteVo.class);
    }

    /**
     * 得到某个股票的最新收盘价
     * @return
     * @throws IOException
     */
    public static List<MinuteQuoteVo> queryRealtimeQuotes(String stockCode, int recentlyCount, FuquanKind fuquanKind) throws Exception {
        String urlString = BASE_URL_QUOTE + "/realtimeQuote/queryRealtimeQuotes.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        params.put("recentlyCount", String.valueOf(recentlyCount));
        params.put("fuquan", fuquanKind.getCode());
        String retStr = httpRequester.sendGet(urlString, params);
        return ReturnMapHandler.handleList(retStr, "list", MinuteQuoteVo.class);
    }

}
