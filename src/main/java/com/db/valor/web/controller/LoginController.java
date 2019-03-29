package com.db.valor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 登录控制器
 * </p>
 *
 * @author daibing
 * @since 2019/3/29
 */
@RequestMapping("/admin")
@Controller
public class LoginController {

    @RequestMapping("")
    public String login(){
        return "login";
    }
}
