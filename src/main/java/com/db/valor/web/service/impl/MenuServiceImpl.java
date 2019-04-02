package com.db.valor.web.service.impl;

import com.db.valor.web.entity.Menu;
import com.db.valor.web.dao.Menudao;
import com.db.valor.web.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<Menudao, Menu> implements MenuService {

    @Autowired
    private Menudao menudao;

    @Override
    public Set<String> listMenuStrByUserId(Long userId) {
        List<Menu> menus = menudao.getMenusByUserId(userId);
        HashSet<String> menuKeys = new HashSet<>();
        for (Menu menu : menus) {
            menuKeys.add(menu.getPerms());
        }
        return menuKeys;
    }
}
