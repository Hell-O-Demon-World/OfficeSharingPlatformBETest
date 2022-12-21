package com.golfzonaca.officesharingplatform.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    private net.sf.ehcache.CacheManager createCacheManager() {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.diskStore(new DiskStoreConfiguration().path("java.io.tmpdir"));
        return net.sf.ehcache.CacheManager.create(configuration);
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {

        net.sf.ehcache.CacheManager manager = this.createCacheManager();

        Cache getMenuCache = new Cache(new CacheConfiguration()
                .maxEntriesLocalHeap(1000)
                .maxEntriesLocalDisk(10000)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .memoryStoreEvictionPolicy("LFU")
                .transactionalMode(CacheConfiguration.TransactionalMode.OFF)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP))
                .name("getMenu")
        );
        manager.addCache(getMenuCache);

        return new EhCacheCacheManager(manager);
    }


    /*@Bean
    public Caffeine caffeineConfig() {
        return Caffeine
                .newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .recordStats();
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }*/

// 상기 : 하나의 설정을 사용
// 하기 : 캐시별로 각각 설정

    /*@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> cacheList = Arrays
                .stream(CacheType.values())
                .map(cache -> new CaffeineCache(
                        cache.getName(),
                        Caffeine.newBuilder()
                                .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.MINUTES)
                                .refreshAfterWrite(cache.getRefreshAfterWrite(), TimeUnit.MINUTES)
                                .maximumSize(cache.getMaximumSize())
                                .recordStats()
                                .build()
                ))
                .collect(Collectors.toList());
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }*//**/


}
