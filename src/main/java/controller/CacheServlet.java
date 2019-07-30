package controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.jboss.as.clustering.infinispan.DefaultCacheContainer;
import util.InfinispanCacheManager;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/too")
public class CacheServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        org.jboss.as.clustering.infinispan.DefaultCacheContainer defaultCacheManager;

        javax.naming.Context ctx = null;
        ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter out = resp.getWriter();
        try {
            ctx = new javax.naming.InitialContext();
            defaultCacheManager = (DefaultCacheContainer) ctx.lookup("java:jboss/infinispan/container/hibernate");
            Cache cache = defaultCacheManager.getCache("user-region");
            String json = objectMapper.writeValueAsString(cache);
            out.print(json);
            out.flush();
            out.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
//        DefaultCacheManager manager = InfinispanCacheManager.getInstance();
//        Cache cache = null;
//        synchronized (manager) {
//            if(!manager.cacheExists("CCfindByPrefixCache")){
//                cache = manager.createCache("CCfindByPrefixCache",new ConfigurationBuilder().build());
//            }else {
//                cache = manager.getCache("CCfindByPrefixCache");
//            }
//        }
//        Object o = cache.get(null);
//
//        if (cache != null) {
//            cache.put("ixtiandr","Vazgeni");
//        }
//
//
//
////        try {
////            javax.naming.Context ctx = new javax.naming.InitialContext();
////            defaultCacheManager = (DefaultCacheContainer) ctx.lookup("java:jboss/infinispan/container/hibernate");
//            ObjectMapper objectMapper = new ObjectMapper();
//            PrintWriter out = resp.getWriter();
////            Set<String> cacheNames = defaultCacheManager.getCacheNames();
//        DefaultCacheManager manager1 = InfinispanCacheManager.getInstance();
//        DefaultCacheManager manager5 = InfinispanCacheManager.getInstance();
//
//        Set<String> cacheNames = manager1.getCacheNames();
//
//        for (String cacheName : cacheNames){
//            out.print(cacheName + "\n");
//            Cache cache1 = manager1.getCache(cacheName);
//            String json = objectMapper.writeValueAsString(cache1);
//            out.print(json + "\n");
//        }
////            for (String cacheName : cacheNames){
////                out.print(cacheName + "\n");
////                Cache cache = defaultCacheManager.getCache(cacheName);
//                String json = objectMapper.writeValueAsString(cache);
//                out.print(json);
////            }
//
//            out.flush();
//            out.close();
////        } catch (NamingException e) {
////            return;
////        }
//
//
    }
}
