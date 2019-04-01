package com.db.valor.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.valor.web.entity.User;
import com.db.valor.web.dao.Userdao;
import com.db.valor.web.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daibing
 * @since 2019-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<Userdao, User> implements UserService {

    @Autowired
    private Userdao userdao;

    @Override
    public User getUserByUsername(String username) {
        return userdao.selectOne(new QueryWrapper<User>().eq("USERNAME", username));
    }
}
