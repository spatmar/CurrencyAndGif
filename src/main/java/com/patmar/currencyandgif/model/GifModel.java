package com.patmar.currencyandgif.model;

import java.util.Map;


public class GifModel {
    private Map[] data;
    private Map pagination;
    private Map meta;

    public GifModel(Map[] data, Map pagination, Map meta) {
        this.data = data;
        this.pagination = pagination;
        this.meta = meta;
    }

    public Map[] getData() {
        return data;
    }

}
