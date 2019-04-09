package com.db.valor.exceptions;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.db.valor.utils.JsonResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author daibing
 * @since 2019/4/1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 验证码全局处理异常
     * @param kaptchaException
     * @return
     */
    @ExceptionHandler(value = KaptchaException.class)
    public JsonResult kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return JsonResult.error("验证码不正确");
        }
        if (kaptchaException instanceof KaptchaNotFoundException) {
            return JsonResult.error("验证码未找到");
        }
        if (kaptchaException instanceof KaptchaTimeoutException) {
            return JsonResult.error("验证码已过期");
        }
        return JsonResult.error("验证码渲染失败");
    }

    /**
     * 用户认证时的全局处理异常
     * @param authenticationException 认证异常
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public JsonResult authenticationExceptionHandler(AuthenticationException authenticationException) {
        if (authenticationException instanceof UnknownAccountException) {
            return JsonResult.error("用户不存在");
        }
        if (authenticationException instanceof IncorrectCredentialsException) {
            return JsonResult.error("密码错误");
        }
        return JsonResult.error("用户名或密码错误");
    }
}
