package com.db.valor.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.db.valor.exceptions.GlobalExceptionHandler;
import com.db.valor.service.UserService;
import com.db.valor.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private Kaptcha kaptcha;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 用户认证,认证失败触发GlobalExceptionHandler
     * @param username 用户名
     * @param password 密码
     * @param validateCode 验证码
     * @param rememberMe 记住我
     * @return JsonResult
     * @see GlobalExceptionHandler
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, String validateCode, boolean rememberMe) {
        if (StringUtils.isBlank(validateCode)) {
            return JsonResult.error("验证码不能为空");
        }
        // 判断验证码是否正确
        kaptcha.validate(validateCode);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        // 执行认证
        subject.login(token);

        return JsonResult.success("登录成功");
    }
}
