package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CacheDao;
import model.Users;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import util.InfinispanCacheManager;


import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Set;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @EJB
    CacheDao cacheDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
////            Set<String> cacheNames = defaultCacheManager.getCacheNames();
//        DefaultCacheManager manager1 = InfinispanCacheManager.getInstance();
//        Set<String> cacheNames = manager1.getCacheNames();
//
//        for (String cacheName : cacheNames){
//            out.print(cacheName + "\n");
//            Cache cache1 = manager1.getCache(cacheName);
//            String json = objectMapper.writeValueAsString(cache1);
//            out.print(json + "\n");
//        }
        Users users = cacheDao.getUser(50);
        out.print(objectMapper.writeValueAsString(users));
        out.flush();
        out.close();

    }
}
