package com.db.valor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.valor.dao.Roledao;
import com.db.valor.entity.Role;
import com.db.valor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
public class RoleServiceImpl extends ServiceImpl<Roledao, Role> implements RoleService {
    @Autowired
    private Roledao roledao;

    @Override
    public List<String> getRolesByUserId(Integer id) {
        List<Role> roles = roledao.selectRolesByUserId(id);
        // 提取角色的id，并去重
        List<String> roldIds = roles.stream()
                .map(Role::getRoleId).distinct()
                .collect(Collectors.toList());
        return roldIds;
    }
}
