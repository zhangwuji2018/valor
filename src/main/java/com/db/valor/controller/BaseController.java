package com.db.valor.controller;

import com.db.valor.entity.User;
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

    protected User getCurrentUser() {
        return ((User) getSubject().getPrincipal());
    }

    protected Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
