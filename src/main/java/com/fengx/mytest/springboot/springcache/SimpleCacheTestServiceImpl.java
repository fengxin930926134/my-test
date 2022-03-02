package com.fengx.mytest.springboot.springcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleCacheTestServiceImpl implements CacheTestService {
    private static final Logger logger = LoggerFactory.getLogger(SimpleCacheTestServiceImpl.class);

    private final String CACHE_KEY = "spring_cache";

    private final Map<String, String> enties = new HashMap<>();

    public SimpleCacheTestServiceImpl() {
        enties.put("1", "this no 1");
    }

    @Autowired
    private CacheManager cacheManager;

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "#id")
    public String get(String id) {
        // 打印使用到的cacheManager
        logger.info("The cacheManager is" + cacheManager);
        return "Get value by id=" + id + ",the value is" + enties.get(id);
    }

    /**
     * 这里我们使用了@CacheEvict注解，cacheNames指定了删除哪个cache中的数据，默认会使用方法的参数作为删除的key
     */
    @Override
    @CacheEvict(value = CACHE_KEY, key = "#id")
    public String delete(String id) {
        return enties.remove(id);
    }

    @Override
    @CachePut(value = CACHE_KEY, key = "#id")
    public String save(String id, String value) {
        logger.info("save value " + value + " with key " + id);
        enties.put(id, value);
        return value;
    }

    @Override
    public String update(String id, String value) {
        return enties.put(id, value);
    }
}