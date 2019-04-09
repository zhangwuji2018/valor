package com.db.valor;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * mybatisplus代码自动生成
 * </p>
 *
 * @author daibing
 * @since 2019/3/29
 */
public class MpGeneratorTest {

    @Test
    public void testShiroEncrypt() throws Exception {
        String s = new SimpleHash("SHA-1", "123456", "8cd50474d2a3c1e88298e91df8bf6f1c", 1024).toString();
        //System.out.println(s);
        //410d5d47a713efa3309558200d8208c6526e8dde
//        byte[] decode = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
//
//        String s1 = new String(decode,"UTF-8");
        System.out.println(s);
    }

    @Test
    public void test(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("daibing");
        gc.setMapperName("%sdao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude("user","role","permission","user_role","role_permission"); // 需要生成的表
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.db.valor");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.224.10.52:3306/zb-shiro?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1234");
        mpg.setDataSource(dsc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //xml生成路径
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources" + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.execute();
    }
}
