package com.mission.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Comment;
import com.mission.course.dao.CommentMapper;
import com.mission.course.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

  @Resource
  private CommentMapper commentMapper;
  @Override
  public List<Comment> getCommentList(Page<Comment> page, Comment commentCondition) {
    return commentMapper.getCommentList(page,commentCondition);
  }
}
