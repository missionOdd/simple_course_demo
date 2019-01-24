package com.mission.course.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mission
 * @date 2018/10/6 0006-15:12
 */
@Slf4j
public class MyException extends RuntimeException{

  private static final long serialVersionUID =1L;

  public MyException(String msg) {
    super(msg);
    log.error(msg);
  }

  public MyException(Class<?> clazz,String msg) {
    super(clazz.getName()+"出现错误"+msg);
    log.error(clazz.getName()+"出现错误"+msg);
  }
}
