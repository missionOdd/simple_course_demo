package com.mission.course;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mission.course.dao")
public class CourseApplication {
  private static final Logger log= LoggerFactory.getLogger(CourseApplication.class);
  public static void main(String[] args) {
    SpringApplication.run(CourseApplication.class, args);
    log.info("========================启动完毕========================");
  }
}
