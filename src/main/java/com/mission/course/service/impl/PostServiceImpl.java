package com.mission.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Post;
import com.mission.course.dao.PostMapper;
import com.mission.course.service.PostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

  @Resource
  private PostMapper postMapper;

  @Override
  public List<Post> getPostList(Page page, Post postCondition) {
    return postMapper.getPostList(page,postCondition);
  }
}
