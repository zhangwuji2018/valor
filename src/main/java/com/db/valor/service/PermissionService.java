package com.db.valor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.valor.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-09
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 通过用户id得到权限集合
     * @param id
     * @return
     */
    List<String> getPermissionsByUserId(Integer id);

    /**
     * 查询菜单集合
     * @param currentUserId 用户id
     * @return
     */
    List<Permission> listAllMenusByUserId(Integer currentUserId);
}
