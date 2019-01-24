package com.mission.course.service.impl;

import com.mission.course.entity.PostDetail;
import com.mission.course.dao.PostDetailMapper;
import com.mission.course.service.PostDetailService;
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
public class PostDetailServiceImpl extends ServiceImpl<PostDetailMapper, PostDetail> implements PostDetailService {


  @Resource
  private PostDetailMapper postDetailMapper;
  @Override
  public PostDetail getPostDetail(Integer postId) {
    PostDetail postDetail = new PostDetail();
    postDetail.setPostId(postId);
    return postDetailMapper.getPostDetail(postDetail);
  }
}
