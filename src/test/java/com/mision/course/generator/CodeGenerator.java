package com.mision.course.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author mission
 * @date 2018/10/4 0004-23:11
 */

public class CodeGenerator {
  /**
  * 代码生成器
  */
  @Test
  @Ignore
  public void testMyBatisPulsGenerator(){
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    //1.全局配置
    GlobalConfig config = new GlobalConfig();
    config.setActiveRecord(true)//是否支持AR
        .setAuthor("mission")//作者
        .setOutputDir("D:\\Codes\\IdeaWorkspaces\\course\\src\\main\\java") //生成路径
        .setFileOverride(false)  //文件覆盖
        .setIdType(IdType.AUTO) //逐渐策略
        .setServiceName("%sService") //设置生成的service接口的首字母是否为I,默认有IxxService
        .setBaseResultMap(true) //生成resultMap
        .setBaseColumnList(true)
        .setSwagger2(true); //生成sql片段

    //2.数据源配置
    DataSourceConfig dsConfig = new DataSourceConfig();
    dsConfig.setDbType(DbType.MYSQL)  //数据库类型
            .setDriverName("com.mysql.jdbc.Driver")
            .setUrl("jdbc:mysql://localhost:3306/quality_course?useUnicode=true&characterEncoding=UTF8&us")
            .setUsername("root")
            .setPassword("root");

    //3.策略配置
    StrategyConfig stConfig = new StrategyConfig();
    stConfig.setCapitalMode(true) //全局大写命名
            .setColumnNaming(NamingStrategy.underline_to_camel)//指定表名 字段名下划线
            .setNaming(NamingStrategy.underline_to_camel)//映射实体类命名策略
            .setTablePrefix("tb_")
            .setInclude("tb_video"
            );//生成表
    /*"tb_comment",
                "tb_file",
                "tb_file_category",
                "tb_file_type",
                "tb_notice",
                "tb_post",
                "tb_post_category",
                "tb_post_detail",
                "tb_site",
                "tb_thumb",
                "tb_user",
                "tb_visit"

     */

    //4.包名策略配置
    PackageConfig pkConfig =new PackageConfig();
    pkConfig.setParent("com.mission.course")
            .setController("web")
            .setService("service")
              .setEntity("entity")
            .setServiceImpl("service.impl")
            .setMapper("dao")
              .setXml("dao");

    //5.整合配置
    mpg.setGlobalConfig(config)
        .setDataSource(dsConfig)
        .setStrategy(stConfig)
        .setPackageInfo(pkConfig);
    //6.执行
    mpg.execute();
}
}
