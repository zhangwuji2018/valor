package com.db.valor.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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

    //@Autowired
    //private UserService userService;
    //
    //@Autowired
    //private RoleService roleService;
    //
    //@Autowired
    //private MenuService menuService;

    /**
     * 授权，获取用户角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //// 获取当前用户的id
        //Long userId = ((User) principals.getPrimaryPrincipal()).getUserId();
        //log.info("当前用户id={}",userId);
        //// 角色列表
        //Set<String> roles = roleService.listRolesStrByUserId(userId);
        //// 功能列表
        //Set<String> menus = menuService.listMenuStrByUserId(userId);
        //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //// 角色和功能添加到info中返回
        //info.setRoles(roles);
        //info.setStringPermissions(menus);
        //return info;
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
        //// 获取前台输入的用户名和密码
        //String username = (String) token.getPrincipal();
        //// 通过用户名查询用户信息
        //User currentUser = userService.getUserByUsername(username);
        //if (currentUser == null) {
        //    throw new UnknownAccountException();
        //}
        //// 认证的实体信息
        //Object credentials = currentUser.getPassword();
        //// 获取盐值用于加密
        //ByteSource salt = ByteSource.Util.bytes(currentUser.getSalt());
        //return new SimpleAuthenticationInfo(username, credentials, salt, getName());
        return null;
    }
}
