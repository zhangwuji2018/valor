package com.db.valor.controller;

import com.db.valor.entity.Permission;
import com.db.valor.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <pre>
 *     首页控制器
 * </pre>
 *
 * @author : daibing
 * @date : 2019/03/31
 */
@Slf4j
@Controller
public class IndexController extends BaseController{

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        log.info("进入首页控制器");
        return "index";
    }

    /**
     * 查询该用户拥有的菜单
     * @return
     */
    @PostMapping("/getMenu")
    @ResponseBody
    public List<Permission> getPermissions() {
        return permissionService.listAllMenusByUserId(getCurrentUserId());
    }
}
