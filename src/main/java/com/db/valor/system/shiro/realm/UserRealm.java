package com.db.valor.system.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.valor.web.entity.User;
import com.db.valor.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义realm，包含认证和授权
 * </p>
 *
 * @author daibing
 * @since 2019/4/1
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权，获取用户角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户认证（登录）
     * @param token 身份认证的token
     * @return 身份认证的信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取前台输入的用户名和密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过用户名查询用户信息
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null) {
            throw new UnknownAccountException();
        }
        if (!currentUser.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        // 认证的实体信息
        Object credentials = currentUser.getPassword();
        // 获取盐值用于加密
        ByteSource salt = ByteSource.Util.bytes(currentUser.getSalt());
        return new SimpleAuthenticationInfo(username, credentials, salt, getName());
    }
}
