package com.db.valor.controller;

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

public class BaseController {
    @ModelAttribute(name = "subject")
    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
