package com.soustock.stockscore.controller;


import com.soustock.stockscore.common.ReturnMapFactory;
import com.soustock.stockscore.service.StockScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xuyufei on 2018/8/11.
 */
@Controller
@RequestMapping("/stockScore")
public class StockScoreController {

    @Autowired
    private StockScoreService stockScoreService;

    @RequestMapping(value = "/queryScore", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryScore(HttpServletRequest request){
        try {
            String stockCode = request.getParameter("stockCode");
            return ReturnMapFactory.succ("result", stockScoreService.queryScore(stockCode));
        } catch (Exception ex){
            return ReturnMapFactory.fail(ex.getMessage());
        }
    }
}
