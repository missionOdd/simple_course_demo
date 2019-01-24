package com.mission.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mission.course.entity.Site;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface SiteMapper extends BaseMapper<Site> {

  Site selectSiteById(Integer id);
}
