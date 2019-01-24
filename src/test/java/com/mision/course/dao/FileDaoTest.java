package com.mision.course.dao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mision.course.BaseTest;
import com.mission.course.dao.FileMapper;
import com.mission.course.entity.File;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mission
 * @date 2018/10/7 0007-11:38
 */
@Slf4j
public class FileDaoTest extends BaseTest {

  @Resource
  private FileMapper fileMapper;

  @Test
  @Ignore
  public void testSelect(){
    Page<File> filePage=new Page<>(1,3);
    File file =new File();

    List<File> fileList=fileMapper.getFileList(filePage, file);
    filePage.setRecords(fileList);
    log.info("{}",fileList);
    System.out.println("ccc:"+fileList);
    System.out.println("aaa:"+filePage.getCurrent());
    System.out.println("xxxx:"+filePage.getRecords());
    log.info("\n  fileList:{} \n getCurrent:{} \n getTotal:{} \n getRecords:{} \n",fileList,filePage.getCurrent(),filePage.getTotal(),filePage.getRecords());
  }
}
