package com.mision.course.dao;

import com.mision.course.BaseTest;
import com.mission.course.dao.SiteMapper;
import com.mission.course.entity.Site;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author mission
 * @date 2018/10/7 0007-10:25
 */
@Slf4j
public class SiteDaoTest extends BaseTest {

  @Resource
  private SiteMapper siteMapper;

  @Test
  @Ignore
  public void testSelect(){
     Site site = siteMapper.selectSiteById(1);
      log.info("{}",site);

  }


}
