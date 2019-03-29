package com.db.valor.web.controller;


import com.db.valor.web.entity.User;
import com.db.valor.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  用户前端控制器
 * </p>
 *
 * @author daibing
 * @since 2019-03-29
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") String id){
        return userService.getById(id);
    }

}

