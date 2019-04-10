package com.db.valor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.valor.entity.Role;

import java.util.List;

/**
 * <p>
 *  角色服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-09
 */
public interface RoleService extends IService<Role> {
    /**
     * 通过用户id得到对应的角色
     * @param id
     * @return
     */
    List<String> getRolesByUserId(Integer id);
}
