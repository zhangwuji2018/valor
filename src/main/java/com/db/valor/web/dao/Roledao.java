package com.db.valor.web.dao;

import com.db.valor.web.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
@Component
public interface Roledao extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<Role> selectListByUserId(Long userId);
}
