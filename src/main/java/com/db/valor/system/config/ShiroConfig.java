package com.db.valor.system.config;

import com.db.valor.system.shiro.realm.UserRealm;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 登录页
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 配置缓存管理器，将EhCache的管理器交给shiro
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager() {
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
     * @return InputStream
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

    /**
     * 凭证匹配器，密码加密
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
     *将自定义realm加入到容器中
     * @return UserRealm
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * rememberMe cookie 效果是重开浏览器后无需重新登录
     * @return SimpleCookie
     */
    private SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * rememberMe管理对象
     * @return CookieRememberMeManager
     */
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    /**
     * 安全管理器（核心）
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * 开启shiro注解通知器
     * @param securityManager 安全管理器对象
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * shiro过滤器配置
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 认证失败跳转到登录页
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // 配置shiro的过滤链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/kaptcha/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        // 除此之外，所有的都要经过认证
        filterChainDefinitionMap.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
