package com.db.valor.common.utils;

import com.db.valor.web.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * <p>
 * shiro的工具类
 * </p>
 *
 * @author daibing
 * @since 2019/4/2
 */
public class ShiroUtil {
    private ShiroUtil(){}

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getCurrentUser() {
        User user = null;
        return user;
    }
}
