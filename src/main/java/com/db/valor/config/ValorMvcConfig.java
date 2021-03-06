package com.db.valor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 *     通用mvc配置类
 * </pre>
 *
 * @author : daibing
 * @date : 2019/03/31
 */
@Configuration
public class ValorMvcConfig implements WebMvcConfigurer {

    @Value("${shiro.successUrl}")
    private String indexUrl;


}
