package project.janbarry.springbootredis.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RedisService {

    Optional<String> getCache(String key);

    Map<String, String> getCaches(List<String> keyList);

    void setCache(String key, String value);

    void setCaches(Map<String, String> keyValue);

    /**
     * lock key for one minute
     *
     * @param key
     * @param value
     * @return
     */
    boolean lockCache(String key, String value);

}
