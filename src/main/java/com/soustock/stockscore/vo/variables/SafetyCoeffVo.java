package com.soustock.stockscore.vo.variables;

/**
 * Created by xuyufei on 2018/8/12.
 * 安全边际变量，包括
 * 价格安全系数 = （当前价格 - 七年内最低价格） / （七年内最高价格 - 七年内最低价格）
 * )（跌破幅度离最大跌幅的距离）、
 * 跌破天数、近一年跌破天数相对一年前的跌破天数占比、跌破次数（3天内连续多次跌破只能算1次），近一年跌破次数与一年前的跌破次数占比
 */
public class SafetyCoeffVo {

    /**
     * 价格安全系数
     */
    private double priceSafetyCoeff;

    /**
     * 时间安全系数
     */
    private double timeSafetyCoeff;

    /**
     * 折腾次数安全
     */
    private double countSafetyCoeff;


    public double getPriceSafetyCoeff() {
        return priceSafetyCoeff;
    }

    public void setPriceSafetyCoeff(double priceSafetyCoeff) {
        this.priceSafetyCoeff = priceSafetyCoeff;
    }

    public double getTimeSafetyCoeff() {
        return timeSafetyCoeff;
    }

    public void setTimeSafetyCoeff(double timeSafetyCoeff) {
        this.timeSafetyCoeff = timeSafetyCoeff;
    }

    public double getCountSafetyCoeff() {
        return countSafetyCoeff;
    }

    public void setCountSafetyCoeff(double countSafetyCoeff) {
        this.countSafetyCoeff = countSafetyCoeff;
    }
}
