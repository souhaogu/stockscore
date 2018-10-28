package com.soustock.stockscore.common;


/**
 * Created by xuyufei on 2018/10/28.
 */
public interface Fetchable {

    /**
     * @return
     */
    Object get(String[] dataKeys) throws Exception;
}
