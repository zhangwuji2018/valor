package com.db.valor;

import com.db.valor.web.dao.Menudao;
import com.db.valor.web.dao.Roledao;
import com.db.valor.web.entity.Menu;
import com.db.valor.web.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author daibing
 * @since 2019/4/2
 */
public class RoleTest extends ValorApplicationTests{

    @Autowired
    Roledao roledao;

    @Autowired
    Menudao menudao;

    @Test
    public void testSelectRoleByUserId() {
        List<Role> roles = roledao.selectListByUserId(1L);
        System.out.println(roles);
    }

    @Test
    public void testSelectMenuByUserId() {
        List<Menu> menus = menudao.getMenusByUserId(1L);
        System.out.println(menus);
    }
}
