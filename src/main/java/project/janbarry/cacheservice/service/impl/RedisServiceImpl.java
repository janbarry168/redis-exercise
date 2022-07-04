package project.janbarry.cacheservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import project.janbarry.cacheservice.service.CacheService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements CacheService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate redisTemplate) {
        // 預設的Serializer會讓key有不需要的prefix, ex: \xac\xed\x00\x05t\x00\x04
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<String> getCache(String key) {
        return Optional.ofNullable((String) redisTemplate.opsForValue().get(key));
    }

    @Override
    public Map<String, String> getCaches(List<String> keyList) {
        Map<String, String> cacheMap = new HashMap<>();
        List<Object> valueList = redisTemplate.opsForValue().multiGet(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            cacheMap.put(keyList.get(i), (String) valueList.get(i));
        }
        return cacheMap;
    }

    @Override
    public void setCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setCaches(Map<String, String> keyValue) {
        redisTemplate.opsForValue().multiSet(keyValue);
    }

    @Override
    public boolean lockCache(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, 1, TimeUnit.MINUTES);
    }

}
