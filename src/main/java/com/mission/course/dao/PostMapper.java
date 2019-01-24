package com.mission.course.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface PostMapper extends BaseMapper<Post> {


  List<Post> getPostList(Page page, @Param("post") Post postCondition);
}
