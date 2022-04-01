package com.patmar.currencyandgif.model;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRatesModel {
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Float> rates;

    public ExchangeRatesModel(String disclaimer, String license, int timestamp, String base, Map<String, Float> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        if (rates != null) {
            this.rates = rates;
        } else {
            this.rates = new HashMap<String, Float>();
        }
    }

    public Map<String, Float> getRates() {
        return rates;
    }
}
