package com.mission.course.common.util;

/**
 * @author mission
 * @date 2018/8/23 0023-1:29
 */
public class PageCalculator {
  /**
   * 计算多页的开始条数
   * @param pageIndex
   * @param pageSize
   * @return
   */
  @Deprecated
  public static int calculateRowIndex(int pageIndex,int pageSize){

    return (pageIndex>0)?(pageIndex-1)*pageSize:0;
  }
}
