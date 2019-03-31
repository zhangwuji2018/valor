package com.db.valor.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *     谷歌验证码控制器
 * </pre>
 *
 * @author : daibing
 * @date : 2019/03/31
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @RequestMapping("/render")
    public void render() {
        kaptcha.render();
    }
}
