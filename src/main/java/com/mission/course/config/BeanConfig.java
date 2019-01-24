package com.mission.course.config;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mission
 * @date 2018/10/5 0005-23:29
 */
@Configuration
public class BeanConfig {

  /**
   * 测试数据生成器
   * @return
   */
  @Bean
  public DataFactory getDataFactory(){
    return new DataFactory();
  }

}
