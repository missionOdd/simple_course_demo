package com.mission.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Comment;
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
public interface CommentMapper extends BaseMapper<Comment> {

  List<Comment> getCommentList(Page<Comment> page, @Param("comment")Comment commentCondition);

}
