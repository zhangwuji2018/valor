package com.db.valor.system.config;

import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *     shiro配置类
 * </pre>
 *
 * @author : daibing
 * @date : 2019/03/31
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置缓存管理器，将EhCache的管理器交给shiro
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        CacheManager cacheManager = CacheManager.getCacheManager("valor");
        if (cacheManager == null) {
            ehCacheManager.setCacheManager(new CacheManager(getEhCacheConfigFile()));
            return ehCacheManager;
        }
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    /**
     * 读取ehcache配置文件
     * @return
     */
    public InputStream getEhCacheConfigFile() {
        String ehcacheFile = "classpath:ehcache.xml";
        try(InputStream inputStream = ResourceUtils.getInputStreamForPath(ehcacheFile)) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            throw new ConfigurationException("读取["+ehcacheFile+"]错误",e);
        }
    }
}
