package com.fengx.mytest.springboot.springcache;

/**
 * 1.Spring cache是代码级的缓存，一般是使用一个ConcurrentMap，也就是说实际上还是是使用JVM的内存来缓存对象的，这势必会造成大量的内存消耗。但好处是显然的：使用方便。
 * 2.Redis 作为一个缓存服务器，是内存级的缓存。它是使用单纯的内存来进行缓存。
 * 3.集群环境下，每台服务器的spring cache是不同步的，这样会出问题的，spring cache只适合单机环境。
 * 4.Redis是设置单独的缓存服务器，所有集群服务器统一访问redis，不会出现缓存不同步的情况。
 */
public class Test {
}
