package com.db.valor.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * <p>
 *     公共的控制器
 * </p>
 *
 * @author daibing
 * @since 2019/4/9
 */
@Slf4j
public class BaseController {
    @ModelAttribute(name = "subject")
    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected String getUsername() {
        return ((String) getSubject().getPrincipal());
    }
}
