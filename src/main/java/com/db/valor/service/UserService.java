package com.db.valor.service;

import com.db.valor.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-09
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
