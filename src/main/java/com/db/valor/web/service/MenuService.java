package com.db.valor.web.service;

import com.db.valor.web.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询用户对应的功能列表
     * @param userId
     * @return
     */
    Set<String> listMenuStrByUserId(Long userId);
}
