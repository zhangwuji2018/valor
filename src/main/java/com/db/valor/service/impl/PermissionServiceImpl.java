package com.db.valor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.valor.dao.Permissiondao;
import com.db.valor.entity.Permission;
import com.db.valor.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daibing
 * @since 2019-04-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<Permissiondao, Permission> implements PermissionService {

    @Autowired
    private Permissiondao permissiondao;

    @Override
    public List<String> getPermissionsByUserId(Integer id) {
        return null;
    }
}
