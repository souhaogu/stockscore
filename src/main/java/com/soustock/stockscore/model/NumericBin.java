package com.soustock.stockscore.model;

/**
 * Created by xuyufei on 2018/10/27.
 * 数字封箱
 */
public class NumericBin {
    private double lower;
    private double upper;
    private double woe;

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public double getWoe() {
        return woe;
    }

    public void setWoe(double woe) {
        this.woe = woe;
    }
}
