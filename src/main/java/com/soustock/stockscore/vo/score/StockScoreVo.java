package com.soustock.stockscore.vo.score;


import java.io.Serializable;

/**
 * Created by xuyufei on 2016/5/1.
 * 股票的得分列表
 */
public class StockScoreVo implements Serializable {

    /**
     * 综合分
     */
    private double totalScore;

    /**
     * 安全分
     */
    private double safetyScore = -1.0;

    /**
     * 财务分
     */
    private double accountScore = -1.0;

    /**
     * 趋势分
     */
    private double directionScore = -1.0;

    /**
     * 活跃分
     */
    private double vitalityScore = -1.0;

    /**
     * 事件分
     */
    private double eventScore = -1.0;


    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }


    public double getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(double safetyScore) {
        this.safetyScore = safetyScore;
    }

    public double getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(double accountScore) {
        this.accountScore = accountScore;
    }

    public double getDirectionScore() {
        return directionScore;
    }

    public void setDirectionScore(double directionScore) {
        this.directionScore = directionScore;
    }

    public double getVitalityScore() {
        return vitalityScore;
    }

    public void setVitalityScore(double vitalityScore) {
        this.vitalityScore = vitalityScore;
    }

    public double getEventScore() {
        return eventScore;
    }

    public void setEventScore(double eventScore) {
        this.eventScore = eventScore;
    }

}
