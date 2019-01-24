package com.mission.course.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mission
 * @date 2018/10/4 0004-21:44
 */
@Configuration
public class MybatisPlusConfig {

  /***
   * plus 的性能优化
   */
  @Bean
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
    performanceInterceptor.setMaxTime(1000);
    /*<!--SQL是否格式化 默认false-->*/
    performanceInterceptor.setFormat(true);
    return performanceInterceptor;
  }

  /**
   *逻辑删除
   * @return
   */
  @Bean
  public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
  }

  /**
   * mybatis-plus分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }


}