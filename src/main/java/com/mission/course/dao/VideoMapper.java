package com.mission.course.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-11
 */
public interface VideoMapper extends BaseMapper<Video> {

  List<Video> getVideoList(Page page, @Param("video")Video video);
}
