package com.mission.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mission.course.entity.PostDetail;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface PostDetailMapper extends BaseMapper<PostDetail> {

  PostDetail getPostDetail( @Param("postDetail")PostDetail postDetailCondition);

}
