package com.mission.course.service;

import com.mission.course.entity.Site;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface SiteService extends IService<Site> {

  Site selectSiteById(Integer id) throws Exception;
}
