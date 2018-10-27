package com.soustock.stockscore.common;

import com.soustock.stockscore.exception.BusinessException;
import com.soustock.stockscore.utils.JsonUtity;
import com.soustock.stockscore.utils.StringUtity;
import com.soustock.stockscore.vo.StockSimpleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by xuyufei on 2018/10/27.
 */
public class ReturnMapHandler {

    public static <T> T handleObject(String retMapStr, String resultKey, Class<T> resultClazz) throws BusinessException {
        if (!StringUtity.isNullOrEmpty(retMapStr)) {
            Map<String, Object> retMap = (Map<String, Object>) JsonUtity.readValue(retMapStr, Map.class);
            if (retMap != null) {
                boolean isSucc = (boolean) retMap.get("isSucc");
                if (isSucc) {
                    Object obj = retMap.get(resultKey);
                    if (obj != null) {
                        return (T) JsonUtity.readValue(obj.toString(), resultClazz);
                    }
                } else {
                    String error = (String) retMap.get("error");
                    if (!StringUtity.isNullOrEmpty(error)) {
                        throw new BusinessException(error);
                    }
                }
            }
        }
        throw new BusinessException("unknown error.");
    }

    public static <T> List<T> handleList(String retMapStr, String resultKey, Class<T> resultElementClazz) throws BusinessException {
        if (!StringUtity.isNullOrEmpty(retMapStr)) {
            Map<String, Object> retMap = (Map<String, Object>) JsonUtity.readValue(retMapStr, Map.class);
            if (retMap != null) {
                boolean isSucc = (boolean) retMap.get("isSucc");
                if (isSucc) {
                    Object obj = retMap.get(resultKey);
                    if (obj != null) {
                        return (List<T>) JsonUtity.readValueToList(obj.toString(), resultElementClazz);
                    }
                } else {
                    String error = (String) retMap.get("error");
                    if (!StringUtity.isNullOrEmpty(error)) {
                        throw new BusinessException(error);
                    }
                }
            }
        }
        throw new BusinessException("unknown error.");
    }
}
