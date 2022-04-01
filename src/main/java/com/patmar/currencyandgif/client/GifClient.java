package com.patmar.currencyandgif.client;

import com.patmar.currencyandgif.model.GifModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "gifModel", url = "${gif.link}")
public interface GifClient {
    @GetMapping("?api_key={apiKey}&q={query}&offset={offset}&bundle=messaging_non_clips")
    GifModel getGif(@PathVariable String apiKey, @PathVariable Integer query, @PathVariable String offset);
}
