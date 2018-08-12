package com.soustock.stockscore.vo.score;

import java.io.Serializable;

/**
 * Created by xuyufei on 2018/8/12.
 * 细节分
 */
public class DetailScoreVo implements Serializable {

    /**
     * 安全分
     */
    private double safetyScore;

    /**
     * 财务分
     */
    private double accountScore;

    /**
     * 趋势分
     */
    private double directionScore;

    /**
     * 活跃分
     */
    private double vitality;

    /**
     * 事件分
     */
    private double eventScore;

    public double getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(double safetyScore) {
        this.safetyScore = safetyScore;
    }

    public double getDirectionScore() {
        return directionScore;
    }

    public void setDirectionScore(double directionScore) {
        this.directionScore = directionScore;
    }

    public double getEventScore() {
        return eventScore;
    }

    public void setEventScore(double eventScore) {
        this.eventScore = eventScore;
    }

    public double getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(double accountScore) {
        this.accountScore = accountScore;
    }

    public double getVitality() {
        return vitality;
    }

    public void setVitality(double vitality) {
        this.vitality = vitality;
    }
}
