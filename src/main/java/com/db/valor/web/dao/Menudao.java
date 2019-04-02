package com.db.valor.web.dao;

import com.db.valor.web.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
@Component
public interface Menudao extends BaseMapper<Menu> {

    /**
     * 查询用户所有的功能
     * @param userId
     * @return
     */
    List<Menu> getMenusByUserId(Long userId);
}
