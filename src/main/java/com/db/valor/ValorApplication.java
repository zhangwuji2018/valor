package com.db.valor;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 项目启动类
 * @author daibing
 * @date 2019/2/28
 */
@Slf4j
@MapperScan("com.db.valor.*.dao")
@SpringBootApplication
@EnableCaching
public class ValorApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ValorApplication.class, args);
        String port = context.getEnvironment().getProperty("server.port");
        String path = context.getEnvironment().getProperty("server.servlet.context-path");
        log.info("valor started at http://localhost:"+port+path);
    }

}
