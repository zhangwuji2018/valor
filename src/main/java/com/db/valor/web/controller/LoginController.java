package com.db.valor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 登录控制器
 * </p>
 *
 * @author daibing
 * @since 2019/3/29
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
