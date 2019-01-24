package com.mision.course.entity;
import com.mision.course.BaseTest;
import com.mission.course.dao.FileMapper;
import com.mission.course.entity.File;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author mission
 * @date 2018/10/5 0005-16:24
 */

public class FileTest extends BaseTest {

  @Resource
  FileMapper fileMapper;

  @Test
  @Ignore
  public void testBase(){
    File file = new File();
    file.setFileName("测试文件");
    file.setFileTitle("");
    file.setFileSrc("xxxxx");
    file.setPriority(0);
    file.setFileDesc("达到");
    file.setLastEditTime(LocalDateTime.now());
    file.setCreateTime(LocalDateTime.now());
    file.insert();

  }

  @Test
  @Ignore
  public void testDetele(){
      File file =new File();
      file.setFileId(2);
      file.deleteById();
  }
}
