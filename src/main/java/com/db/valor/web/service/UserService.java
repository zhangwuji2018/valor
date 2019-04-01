package com.db.valor.web.service;

import com.db.valor.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author daibing
 * @since 2019-03-29
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
