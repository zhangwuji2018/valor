package com.db.valor.web.service;

import com.db.valor.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return User
     */
    User getUserByUsername(String username);
}
