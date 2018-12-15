package com.natay.mareksan.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;


@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    public static final String ORDERS = "orderCache";
    public static final String ORDERTYPES  = "orderType";
    public static final String ORDERSTATUS  = "orderStatus";


    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager= new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache(ORDERS),
                new ConcurrentMapCache(ORDERTYPES),
                new ConcurrentMapCache(ORDERSTATUS)
        ));
        return cacheManager;
    }

    @CacheEvict(allEntries = true,value = {ORDERS})
    @Scheduled(fixedDelay = 10*60*1000,initialDelay = 500)
    public void cacheEvictEveryTenMinutes(){LOGGER.info("cacheEvictEveryTenMinutes");}
    
    @CacheEvict(allEntries = true,value = {ORDERTYPES,ORDERSTATUS})
    @Scheduled(fixedDelay = 6*60*60*1000,initialDelay = 500)
    public void cacheEvictEverySixHours(){LOGGER.info("cacheEvictEverySixHours");}
}
