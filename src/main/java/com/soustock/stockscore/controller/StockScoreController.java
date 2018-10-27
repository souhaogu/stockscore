package com.soustock.stockscore.controller;


import com.soustock.stockscore.service.StockScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyufei on 2018/8/11.
 */
@Controller
@RequestMapping("/stockScore")
public class StockScoreController {

    @Autowired
    private StockScoreService stockScoreService;

    @RequestMapping(value = "/queryLongScore", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryLongScore(HttpServletRequest request) throws Exception {
        String stockCode = request.getParameter("stockCode");
        Map<String, Object> map = new HashMap<>();
        map.put("isSucc", "true");
        map.put("result", stockScoreService.queryLongScore(stockCode));
        return map;
    }
}
