package com.mission.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Thumb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface ThumbMapper extends BaseMapper<Thumb> {

  List<Thumb> getThumbList(Page page,@Param("thumb") Thumb thumb);
}
