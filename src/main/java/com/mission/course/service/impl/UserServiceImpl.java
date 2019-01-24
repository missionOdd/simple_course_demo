package com.mission.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.entity.Thumb;
import com.mission.course.entity.User;
import com.mission.course.dao.UserMapper;
import com.mission.course.service.ThumbService;
import com.mission.course.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Resource
  private UserMapper userMapper;

  @Resource
  private ThumbService thumbService;

  @Override
  public boolean login(User user, HttpServletRequest request) {
    //TODO shrio权限
    User usertmp =user.selectOne(new QueryWrapper(user));
    if (usertmp!=null){
      request.getSession().setAttribute("userId",user.getUserId());
      request.getSession().setAttribute("username",user.getUsername());
      return true;
    }
    return false;
  }

  @Override
  public boolean deteleUser(Integer userId) {
    User user =userMapper.selectById(userId);
    Thumb thumb = new Thumb();
    thumb.setThumbId(user.getThumbId());

    if (userMapper.deleteById(userId)>0&& thumbService.deleteThumb(thumb)) {
      return true;
    }
    return false;
  }

  @Override
  public User getOneUser(Integer userId) {
    return userMapper.getOneUser(userId);
  }
}
