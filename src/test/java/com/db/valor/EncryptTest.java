package com.db.valor;

import com.db.valor.web.dao.Userdao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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
    public void testShiroEncrypt() throws Exception {
        String s = new SimpleHash("SHA-1", "123456", "valor", 1024).toString();
        //System.out.println(s);
        //410d5d47a713efa3309558200d8208c6526e8dde
//        byte[] decode = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
//
//        String s1 = new String(decode,"UTF-8");
        System.out.println(s);
    }

    @Test
    public void testShrio() {

    }
}
