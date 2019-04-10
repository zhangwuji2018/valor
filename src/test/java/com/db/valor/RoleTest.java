package com.db.valor;

import com.db.valor.dao.Permissiondao;
import com.db.valor.service.PermissionService;
import com.db.valor.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    Permissiondao permissiondao;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Test
    public void testGetMenusByUserId() {
        System.out.println(permissionService.getPermissionsByUserId(1));
    }

    @Test
    public void testGetRole() {
        System.out.println(roleService.getRolesByUserId(1));
    }
}
