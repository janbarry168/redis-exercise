package project.janbarry.cacheservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface CacheTestController {

    @GetMapping("/query/key/{key}")
    String getCache(@PathVariable String key);

    @PostMapping("/query/keys")
    Map<String, String> getCaches(@RequestBody List<String> keyList);

    @PostMapping("/save/{key}/{value}")
    void setCache(@PathVariable String key, @PathVariable String value);

    @PostMapping("/save/keys")
    void setCaches(@RequestBody Map<String, String> keyValue);

    @PostMapping("/lock/{key}/{value}")
    void lockCache(@PathVariable String key, @PathVariable String value) throws Exception ;

}
