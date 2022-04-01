package com.patmar.currencyandgif.controller;

import com.patmar.currencyandgif.client.GifClient;
import com.patmar.currencyandgif.model.GifModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/")
public class GifController {

    @Autowired
    private GifClient gifClient;

    @Autowired
    private ExchangeRatesController exchangeRatesController;

    @Value("${gif.api_key}")
    private String apiKey;

    @Value("${gif.up}")
    private String upRate;

    @Value("${gif.down}")
    private String downRate;

    @Value("${gif.same}")
    private String sameRate;

    @RequestMapping("/{money}")
    public String showGif(@PathVariable String money) {
        float todayValue = Float.parseFloat(exchangeRatesController.todayMoney(money));
        float yesterdayValue = Float.parseFloat(exchangeRatesController.yesterdayMoney(money));

        String request = sameRate;
        if (todayValue > yesterdayValue) {
            request = upRate;
        } else if (todayValue < yesterdayValue) {
            request = downRate;
        }

        GifModel response = gifClient.getGif(apiKey, new Random().nextInt(4999), request);

        String url = (String) ((Map)((Map) response.getData()[0]
                .get("images"))
                .get("original"))
                .get("url");

        return "<html>" +
                "<head>" +
                "</head>" +
                "<body>" +
                "<h1>" + request + "</h1>" +
                "<img src=\"" + url + "\">" +
                "</body>" +
                "</html>";
    }
}
