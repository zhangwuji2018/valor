package com.db.valor.web.service.impl;

import com.db.valor.web.entity.Role;
import com.db.valor.web.dao.Roledao;
import com.db.valor.web.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<Roledao, Role> implements RoleService {

    @Autowired
    private Roledao roledao;

    @Override
    public Set<String> listRolesStrByUserId(Long userId) {
        List<Role> roles = roledao.selectListByUserId(userId);
        HashSet<String> roleKeys = new HashSet<>();
        for (Role role : roles) {
            roleKeys.add(role.getRoleKey());
        }
        return roleKeys;
    }
}
