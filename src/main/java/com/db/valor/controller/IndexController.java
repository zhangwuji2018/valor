package com.db.valor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 *     首页控制器
 * </pre>
 *
 * @author : daibing
 * @date : 2019/03/31
 */
@Slf4j
@Controller
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String index() {
        log.info("进入首页控制器");

        return "index";
    }
}
