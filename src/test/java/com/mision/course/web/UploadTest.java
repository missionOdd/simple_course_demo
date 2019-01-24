package com.mision.course.web;

import com.mision.course.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * @author mission
 * @date 2018/10/6 0006-21:36
 */

public class UploadTest extends BaseTest {

  @Value("${web.upload-path}")
  private String path;

  /** 文件上传测试 */
  @Test
  public void uploadTest() throws Exception {
    File f = new File("D:\\projectdev\\course\\upload\\1.png");
    FileCopyUtils.copy(f, new File(path+"/2.png"));
  }
}

