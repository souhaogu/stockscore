package com.soustock.stockscore.model;


import com.soustock.stockscore.utils.CsvFileReader;
import com.soustock.stockscore.utils.RoundUtity;
import com.soustock.stockscore.vo.DayQuoteVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.util.*;

/**
 * Created by xuyufei on 2018/10/27.
 */
public class SafetyScoreModel {

    private final static Log logger = LogFactory.getLog(SafetyScoreModel.class);

    private static TreeMap<Double, NumericBin> x1_binMap;
    private static TreeMap<Double, NumericBin> x2_binMap;

    static {
        try {
            x1_binMap = readBinsFromCsv("/root/stock_score/x1_woe.csv");
            x2_binMap = readBinsFromCsv("/root/stock_score/x2_woe.csv");
        } catch (Exception ex){
            logger.error(ex);
        }
    }

    private final static double coeff_x1 = 0.9578;
    private final static double coeff_x2 = 0.0531;
    private final static double intercept = -0.3015;
    private final static double b = 62.0000;
    private final static double o = 1.640;
    private final static double p = 13.0000;
    private final static double factor = p / Math.log(2.0);
    private final static double offset = b - p * Math.log(o) / Math.log(2.0);

    public double getScore(double curPrice, List<DayQuoteVo> dayQuoteVoList) {
        Map<String, Double> variablesMap = extractVariables(curPrice, dayQuoteVoList);
        double x1 = variablesMap.get("pricePosPer");
        double x2 = variablesMap.get("priceTimePer");
        double woe_x1 = x1_binMap.floorEntry(x1).getValue().getWoe();
        double woe_x2 = x2_binMap.floorEntry(x2).getValue().getWoe();
        return calcScore(coeff_x1, coeff_x2, intercept, woe_x1, woe_x2);
    }

    private static double calcScore(double x1_coeff, double x2_coeff, double intercept, double x1_woe, double x2_woe) {
        double score = (x1_woe * x1_coeff + x2_woe * x2_coeff + intercept) * factor + offset;
        score = score - 3 + randDouble(6);
        if (score <1.00){
            score = 1.00;
        } else if (score > 99.0){
            score = 99.0;
        }
        score = 60 + (score - 60) * 0.90;
        return RoundUtity.RoundDouble(score, 2);
    }

    private static double randDouble(int n){
        Random rand = new Random();
        return rand.nextDouble() * n;
    }

    /**
     * 读取封箱好的数据
     * @param filePathStr
     * @return
     * @throws IOException
     */
    private static TreeMap<Double, NumericBin> readBinsFromCsv(String filePathStr) throws IOException {
        TreeMap<Double, NumericBin> binTreeMap = new TreeMap<>();
        CsvFileReader csvFileReader = new CsvFileReader(filePathStr);
        try {
            while (csvFileReader.nextRow()) {
                double min = Double.parseDouble(csvFileReader.readField("min"));
                double max = Double.parseDouble(csvFileReader.readField("max"));
                double woe = Double.parseDouble(csvFileReader.readField("woe"));
                NumericBin numericBin = new NumericBin();
                numericBin.setLower(min);
                numericBin.setUpper(max);
                numericBin.setWoe(woe);
                binTreeMap.put(min, numericBin);
            }
            return binTreeMap;
        } finally {
            csvFileReader.close();
        }
    }


    /**
     * 抽取变量值列表
     * @param curPrice
     * @param dayQuoteVoList
     * @return
     */
    private static Map<String, Double> extractVariables(double curPrice, List<DayQuoteVo> dayQuoteVoList){
        double maxPrice = 0;
        double minPrice = 999999.0;
        int totalDayCount = dayQuoteVoList.size();
        int lowerDayCount = 0;
        for (DayQuoteVo dayQuoteVo: dayQuoteVoList){
            double dTemp = dayQuoteVo.getClosePrice();
            if (dTemp > maxPrice){
                maxPrice = dTemp;
            }
            if (dTemp < minPrice){
                minPrice = dTemp;
            }
            if (dTemp < curPrice){
                lowerDayCount ++;
            }
        }
        double pricePosPer = (curPrice - minPrice) * 1.00/(maxPrice - minPrice);
        double priceTimePer = lowerDayCount * 1.00/ totalDayCount;
        Map<String, Double> retMap = new HashMap<>();
        retMap.put("pricePosPer", pricePosPer);
        retMap.put("priceTimePer", priceTimePer);
        return retMap;
    }

}
