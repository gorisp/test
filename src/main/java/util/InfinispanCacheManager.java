package util;

import org.infinispan.manager.DefaultCacheManager;

import javax.ejb.Singleton;


public class InfinispanCacheManager {

    private static final DefaultCacheManager cacheManager = new DefaultCacheManager();

    public static DefaultCacheManager getInstance(){
       return cacheManager;
    }

}
