package com.patmar.currencyandgif.controller;

import com.patmar.currencyandgif.client.ExchangeRatesClient;
import com.patmar.currencyandgif.model.ExchangeRatesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesClient ratesclient;

    @Value("${openexchangerates.api_key}")
    private String apiKey;

    @Value("${openexchangerates.base}")
    private String base;

    @RequestMapping("/getToday")
    public String today() {
        ExchangeRatesModel ratesModel = ratesclient.getToday(apiKey, base);
        StringBuilder stringBuilder = new StringBuilder("Курс валют сегодня<br>\n");
        for (Map.Entry<String, Float> entry : ratesModel.getRates().entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("<br>\n");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/getToday/{money}")
    public String todayMoney(@PathVariable String money) {
        ExchangeRatesModel ratesModel = ratesclient.getToday(apiKey, base);
        return String.valueOf(checkInput(ratesModel.getRates(), money));
    }

    @RequestMapping("/getYesterday")
    public String yesterday() {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1);
        String dataFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateTime);
        ExchangeRatesModel ratesModel = ratesclient.getYesterday(dataFormatted, apiKey, base);
        StringBuilder stringBuilder = new StringBuilder("Курс валют за ").append(dataFormatted).append("<br>\n");
        for (Map.Entry<String, Float> entry : ratesModel.getRates().entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("<br>\n");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/getYesterday/{money}")
    public String yesterdayMoney(@PathVariable String money) {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1);
        String dataFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateTime);
        ExchangeRatesModel ratesModel = ratesclient.getYesterday(dataFormatted, apiKey, base);
        return String.valueOf(checkInput(ratesModel.getRates(), money));
    }

    float checkInput(Map<String, Float> map, String money) {
        if (map.isEmpty()) return 0;
        if (map.containsKey(money)) {
            return map.get(money);
        } else {
            return 0;
        }
    }

}
