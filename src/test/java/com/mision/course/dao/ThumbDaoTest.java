package com.mision.course.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mision.course.BaseTest;
import com.mission.course.dao.ThumbMapper;
import com.mission.course.entity.Thumb;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mission
 * @date 2018/10/15 0015-17:14
 */
@Slf4j
public class ThumbDaoTest extends BaseTest {

  @Resource
  private ThumbMapper thumbMapper;

  @Test
  public void testSelect(){
    Page page =new Page(1,8);
    List<Thumb> thumbList =thumbMapper.getThumbList(page,null);
    log.info("{}",thumbList);
  }
}
