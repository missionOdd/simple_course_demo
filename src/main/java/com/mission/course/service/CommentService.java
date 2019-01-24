package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mission.course.entity.Comment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface CommentService extends IService<Comment> {

  List<Comment> getCommentList(Page<Comment> page, Comment commentCondition);

}
