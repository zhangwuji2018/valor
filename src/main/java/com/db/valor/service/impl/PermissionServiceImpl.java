package com.db.valor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.valor.dao.Permissiondao;
import com.db.valor.entity.Permission;
import com.db.valor.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Permission> permissions = permissiondao.selectPermissionByUserId(id);
        List<String> perms = permissions.stream()
                .map(Permission::getPerms)
                .distinct().collect(Collectors.toList());
        return perms;
    }

    @Override
    public List<Permission> listAllMenusByUserId(Integer currentUserId) {
        return permissiondao.getMenusByUserId(currentUserId);
    }
}
