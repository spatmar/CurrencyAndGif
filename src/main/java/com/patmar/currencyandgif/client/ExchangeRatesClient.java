package com.patmar.currencyandgif.client;

import com.patmar.currencyandgif.model.ExchangeRatesModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "ratesclient", url = "${openexchangerates.link}")
public interface ExchangeRatesClient {
    @GetMapping("latest.json?app_id={appId}&base={base}")
    ExchangeRatesModel getToday(@PathVariable String appId, @PathVariable String base);

    @GetMapping("historical/{date}.json?app_id={appId}&base={base}")
    ExchangeRatesModel getYesterday(@PathVariable String date, @PathVariable String appId, @PathVariable String base);
}
