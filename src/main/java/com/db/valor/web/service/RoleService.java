package com.db.valor.web.service;

import com.db.valor.web.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询所对应的角色列表
     * @param userId 用户id
     * @return 角色列表
     */
    Set<String> listRolesStrByUserId(Long userId);
}
