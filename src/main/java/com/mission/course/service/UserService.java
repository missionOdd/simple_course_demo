package com.mission.course.service;

import com.mission.course.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface UserService extends IService<User> {


  boolean login(User user, HttpServletRequest request);

  boolean deteleUser(Integer userId);

  User getOneUser(Integer userId);

}
