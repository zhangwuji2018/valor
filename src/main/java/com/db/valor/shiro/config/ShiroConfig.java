package com.db.valor.shiro.config;

import com.db.valor.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

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
     *
     * @return EhCacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 凭证匹配器，密码加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 散列的算法
        matcher.setHashAlgorithmName("SHA-1");
        // 散列的次数
        matcher.setHashIterations(1024);
        return matcher;
    }

    /**
     * 将自定义realm加入到容器中
     *
     * @return UserRealm
     */
    @Bean
    public Realm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 配置RememberMeManager
     *
     * @param rememberMeCookieTemplate cookie模板
     * @return
     */
    @Bean
    public RememberMeManager rememberMeManager(Cookie rememberMeCookieTemplate) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookieTemplate);
        // cookie加密密钥,不配置重启都会重新生成一对密钥,控制台会报错
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }

    /**
     * shiro过滤器配置
     * @return ShiroFilterChainDefinition
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 配置shiro的过滤链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/kaptcha/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        // 除此之外，所有的都要经过认证
        filterChainDefinitionMap.put("/**", "user");
        chainDefinition.addPathDefinitions(filterChainDefinitionMap);
        return chainDefinition;
    }
}
