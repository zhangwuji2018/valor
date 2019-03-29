package com.db.valor;

import com.db.valor.web.dao.Userdao;
import com.db.valor.web.entity.User;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 测试加密
 * </p>
 *
 * @author daibing
 * @since 2019/3/29
 */
public class EncryptTest extends ValorApplicationTests{

    @Autowired
    StringEncryptor encryptor;

    @Autowired
    DataSource dataSource;

    @Autowired
    Userdao userdao;
    @Test
    public void testEncryptor() {
        System.out.println("root加密之后："+encryptor.encrypt("root"));
        System.out.println("1234加密之后："+encryptor.encrypt("1234"));
    }

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testSelectUser() {
        List<User> users = userdao.selectList(null);
        System.out.println(users);
    }
}
