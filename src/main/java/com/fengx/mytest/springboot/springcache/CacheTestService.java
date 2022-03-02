package com.fengx.mytest.springboot.springcache;

public interface CacheTestService {

    String get(String id);

    String delete(String id);

    String save(String id, String value);

    String update(String id, String value);
}
