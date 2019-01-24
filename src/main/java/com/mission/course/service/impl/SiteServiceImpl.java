package com.mission.course.service.impl;

import com.mission.course.entity.Site;
import com.mission.course.dao.SiteMapper;
import com.mission.course.service.SiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

  @Resource
  SiteMapper siteMapper;
  @Override
  public Site selectSiteById(Integer id) throws Exception {
    return siteMapper.selectSiteById(id);
  }
}
