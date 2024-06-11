
package com.w2m.challenge.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    public static final String SPACESHIP_CACHE = "spaceship_cache";

    @Bean
    public CacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache(
                        SPACESHIP_CACHE,
                        Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).maximumSize(100).build().asMap(),
                        false
                )
        ));

        return cacheManager;
    }


}

