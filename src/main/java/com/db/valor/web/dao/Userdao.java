package com.db.valor.web.dao;

import com.db.valor.web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author daibing
 * @since 2019-04-02
 */
@Component
public interface Userdao extends BaseMapper<User> {

}
