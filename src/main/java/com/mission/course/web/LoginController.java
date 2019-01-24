package com.mission.course.web;

import com.mission.course.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mission
 * @date 2018/10/25 0025-21:19
 */
@Controller
public class LoginController {

  @Resource
  private UserService userService;

  @GetMapping("/login")
  public String login(){
    return "/frt/login";
  }

  @RequestMapping("/tologin")
  public String login(String username, String password, boolean rememberMe , Model model, HttpServletRequest request){
    /**
     * 使用shiro编写认证操作
     */
    //1.获取Subject
    Subject subject = SecurityUtils.getSubject();

    //2.封装用户数据
    UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);

    //3.执行登录方法
    try {
      subject.login(token);
      request.getSession().setAttribute("user",subject.getPrincipal());
      //30分钟有效
      request.getSession().setMaxInactiveInterval(30*60);
    } catch (UnknownAccountException e) {
      model.addAttribute("msg","用户名不存在");
      return "/login";
    }catch (IncorrectCredentialsException e){
      model.addAttribute("msg","密码错误");
      return "/login";
    }
    //验证是否登录成功
    if(subject.isAuthenticated()) {

      return "/frontend/index";
    }
    return "/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request){
    //获取Subject
    Subject subject = SecurityUtils.getSubject();
    //清空session信息
    if (request.getSession().getAttribute("user")!=null) {
      request.getSession().removeAttribute("user");
      subject.logout();
    }
    return "redirect:/frontend/index";
  }
}
