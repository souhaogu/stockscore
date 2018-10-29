package com.soustock.stockscore.common;


import com.soustock.stockscore.utils.DateUtity;
import com.soustock.stockscore.utils.StringUtity;
import org.springframework.cache.Cache;

import java.util.Date;

/**
 * Created by xuyufei on 2018/10/28.
 */
public class CacheAdapter {

    private Cache cache;
    private Fetchable fetchable;

    public CacheAdapter(Cache cache, Fetchable fetchable){
        this.cache = cache;
        this.fetchable = fetchable;
    }

    /**
     * 从cache获取值
     * @param cache
     * @param key
     * @return
     */
    public static Object getValueFromCache(Cache cache, String key) {
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper != null) {
            return valueWrapper.get();
        }
        return null;
    }

    public Object fetchDataFromCacheOrNot(String[] dataKeys) throws Exception {
        String dataKeyArrStr = String.join("_", dataKeys);
        String cacheDateKey = dataKeyArrStr + "_" + Constants.DATA_KEY_CACHE_DATE;
        String cacheDataKey = dataKeyArrStr + "_" + Constants.DATA_KEY_DATA_CONTENT;
        String cacheDate = (String) getValueFromCache(cache, cacheDateKey);
        String todayStr = DateUtity.dateToDateStr(new Date());
        if ((StringUtity.isNullOrEmpty(cacheDate) || cacheDate.compareTo(todayStr) != 0)) {
            Object obj = fetchable.get(dataKeys);
            cache.put(cacheDateKey, todayStr);
            cache.put(cacheDataKey, obj);
            return obj;
        } else {
            return getValueFromCache(cache, cacheDataKey);
        }
    }
}
