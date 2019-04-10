package com.db.valor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.valor.entity.Role;
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
public interface Roledao extends BaseMapper<Role> {
    /**
     * 通过用户id得到角色列表
     * @param id
     * @return
     */
    List<Role> selectRolesByUserId(Integer id);
}
