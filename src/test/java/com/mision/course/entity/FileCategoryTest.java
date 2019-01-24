package com.mision.course.entity;

import com.mision.course.BaseTest;
import com.mission.course.dao.FileCategoryMapper;
import com.mission.course.entity.FileCategory;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author mission
 * @date 2018/10/5 0005-20:41
 */

public class FileCategoryTest extends BaseTest {


  @Resource
  FileCategoryMapper fileCategoryMapper;

  @Test
  @Ignore
  public void testBase(){
    FileCategory fileCategory =new FileCategory();
    fileCategory.setFileCategoryName("压缩包");
    fileCategory.setFileCategoryDesc("测试测试");
    fileCategory.setFileCategoryStatus(1);
    fileCategory.insert();


  }
}
