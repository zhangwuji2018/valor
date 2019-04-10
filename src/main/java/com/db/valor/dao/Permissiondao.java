package com.db.valor.dao;

import com.db.valor.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author daibing
 * @since 2019-04-09
 */
@Component
public interface Permissiondao extends BaseMapper<Permission> {
    /**
     * 查询所有权限集合
     * @param id
     * @return
     */
    List<Permission> selectPermissionByUserId(Integer id);

    /**
     * 获取所有的菜单
     * @param username 用户名称
     * @return
     */
    List<Permission> getMenusByUsername(String username);
}
