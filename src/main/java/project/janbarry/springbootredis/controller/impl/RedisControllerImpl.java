package project.janbarry.springbootredis.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import project.janbarry.springbootredis.controller.RedisController;
import project.janbarry.springbootredis.controller.exception.ControllerException;
import project.janbarry.springbootredis.service.RedisService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cache")
public class RedisControllerImpl implements RedisController {

    private final RedisService cacheService;

    @Autowired
    public RedisControllerImpl(RedisService cacheService) {
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
        boolean lockStatus = cacheService.lockCache(key, value);
        if (!lockStatus) {
            throw new ControllerException("lockCache fail!", "key already exist: " + key);
        }
    }

}
