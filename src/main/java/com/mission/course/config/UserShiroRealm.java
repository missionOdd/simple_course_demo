package com.mission.course.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.entity.Thumb;
import com.mission.course.entity.User;
import com.mission.course.service.ThumbService;
import com.mission.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author mission
 * @date 2018/10/25 0025-9:30
 */
@Slf4j
public class UserShiroRealm extends AuthorizingRealm {

  @Resource
  private UserService userService;

  @Resource
  private ThumbService thumbService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
  log.debug("执行授权逻辑");
    //给资源进行授权
    SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
    //获取User
    Subject subject = SecurityUtils.getSubject();
    User dbUser= (User) subject.getPrincipal();
    dbUser.setLastLoginTime(LocalDateTime.now());
    dbUser.updateById();

    //添加权限
    info.addRole(dbUser.getRole());
    info.addStringPermission(dbUser.getPermission());

    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    log.debug("执行认证逻辑");

    //判断用户名
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

    User user=new User();
    user.setUsername(token.getUsername());
    User dbUser =userService.getOne(new QueryWrapper<>(user));
    if (dbUser==null){//用户不存在
      return null;
    }
    Thumb thumb =thumbService.getById(dbUser.getThumbId());
    dbUser.setThumb(thumb);
    //判断密码
    return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),"");
  }
}
