package com.db.valor.web.controller;

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
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {

        return "index";
    }
}
