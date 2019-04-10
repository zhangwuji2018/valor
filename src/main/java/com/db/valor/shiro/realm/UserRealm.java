package com.db.valor.shiro.realm;

import com.db.valor.entity.User;
import com.db.valor.service.PermissionService;
import com.db.valor.service.RoleService;
import com.db.valor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 自定义realm，包含认证和授权
 * </p>
 *
 * @author daibing
 * @since 2019/4/1
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权，获取用户角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户的id
        Integer id = ((User) principals.getPrimaryPrincipal()).getId();
        log.info("当前用户id={}",id);
        // 角色列表
        List<String> roles = roleService.getRolesByUserId(id);
        // 功能列表
        List<String> permissions = permissionService.getPermissionsByUserId(id);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色和功能添加到info中返回
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
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
        // 通过用户名查询用户信息
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null) {
            throw new UnknownAccountException();
        }
        // 认证的实体信息
        Object credentials = currentUser.getPassword();
        // 获取盐值用于加密
        ByteSource salt = ByteSource.Util.bytes(currentUser.getSalt());
        return new SimpleAuthenticationInfo(username, credentials, salt, getName());
    }
}
