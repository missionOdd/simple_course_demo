package com.mission.course.dao;

import com.mission.course.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface UserMapper extends BaseMapper<User> {


  User getOneUser(@Param("userId") Integer userId);
}
