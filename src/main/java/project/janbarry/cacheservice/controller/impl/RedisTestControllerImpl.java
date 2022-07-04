package project.janbarry.cacheservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import project.janbarry.cacheservice.controller.CacheTestController;
import project.janbarry.cacheservice.controller.exception.ControllerException;
import project.janbarry.cacheservice.service.CacheService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cache")
public class RedisTestControllerImpl implements CacheTestController {

    private final CacheService cacheService;

    @Autowired
    public RedisTestControllerImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public String getCache(String key) {
        Optional<String> valueOpt = cacheService.getCache(key);
        return valueOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Map<String, String> getCaches(List<String> keyList) {
        return cacheService.getCaches(keyList);
    }

    @Override
    public void setCache(String key, String value) {
        cacheService.setCache(key, value);
    }

    @Override
    public void setCaches(Map<String, String> keyValue) {
        cacheService.setCaches(keyValue);
    }

    @Override
    public void lockCache(String key, String value) {
        boolean result = cacheService.lockCache(key, value);
        if (!result) {
            throw new ControllerException(HttpStatus.OK, "lockCache fail!", "key already exist: " + key);
        }
    }

}
