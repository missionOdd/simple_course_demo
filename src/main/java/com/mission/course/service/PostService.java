package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface PostService extends IService<Post> {

  List<Post> getPostList(Page page, Post postCondition);
}
