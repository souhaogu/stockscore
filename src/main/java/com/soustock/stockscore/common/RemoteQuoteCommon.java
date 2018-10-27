package com.soustock.stockscore.common;


import com.alibaba.fastjson.JSON;
import com.soustock.stockscore.utils.HttpRequester;
import com.soustock.stockscore.utils.JsonUtity;
import com.soustock.stockscore.utils.StringUtity;
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
    public static StockSimpleVo getStockSimpleVoByStockCode(String stockCode) throws IOException {
        String urlString = BASE_URL_QUOTE + "/stockBasic/getStockBasicByStockCode.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        String retStr = httpRequester.sendGet(urlString, params);
        Map<String, Object> retMap = JSON.parseObject(retStr, Map.class);
        if (retMap != null){
            StockSimpleVo stockSimpleVo = (StockSimpleVo) JsonUtity.readValue(retMap.get("result").toString(), StockSimpleVo.class);
            return stockSimpleVo;
        }
        return null;
    }


    /**
     * 得到某个股票的起止日期的日行情数据
     * @return
     * @throws IOException
     */
    public static List<DayQuoteVo> queryQuoteByDate(String stockCode, String bgnDate, String endDate, String fuquan) throws IOException {
        String urlString = BASE_URL_QUOTE + "/dayQuote/queryQuoteByDate.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        params.put("bgnDate", bgnDate);
        params.put("endDate", endDate);
        params.put("fuquan", fuquan);
        String retStr = httpRequester.sendGet(urlString, params);
        if (!StringUtity.isNullOrEmpty(retStr)){
            Map<String, Object> retMap = JSON.parseObject(retStr, Map.class);
            return (List<DayQuoteVo>)JsonUtity.readValueToList(retMap.get("list").toString(), DayQuoteVo.class);
        }
        return null;
    }

    /**
     * 得到某个股票的最新收盘价
     * @return
     * @throws IOException
     */
    public static List<MinuteQuoteVo> queryRealtimeQuotes(String stockCode, int recentlyCount) throws IOException {
        String urlString = BASE_URL_QUOTE + "/realtimeQuote/queryQuoteData.do";
        HttpRequester httpRequester = new HttpRequester();
        Map<String, String> params = new HashMap<>();
        params.put("stockCode", stockCode);
        params.put("recentlyCount", String.valueOf(recentlyCount));
        String retStr = httpRequester.sendGet(urlString, params);
        if (!StringUtity.isNullOrEmpty(retStr)){
            Map<String, Object> retMap = JSON.parseObject(retStr, Map.class);
            return (List<MinuteQuoteVo>) JsonUtity.readValueToList(retMap.get("list").toString(), MinuteQuoteVo.class);
        }
        return null;
    }

}
