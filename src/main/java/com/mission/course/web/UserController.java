package com.mission.course.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.common.util.VerifyCodeUtil;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Thumb;
import com.mission.course.entity.User;
import com.mission.course.service.ThumbService;
import com.mission.course.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Resource
  private UserService userService;

  @Resource
  private ThumbService thumbService;

  @GetMapping("getuserlist")
  public JsonResult<User> getUserList(){

    List<User> users =userService.list(null);
    log.info("getUserList: \n {}",users);
    if (users==null){
      return ResultFactory.empty();
    }
    return ResultFactory.ok(users);
  }


  @GetMapping("getauthorlist")
  public JsonResult<User> getAuthorList(){

    List<User> users =userService.list(new QueryWrapper<User>().like("role","author"));
    log.info("getUserList: \n {}",users);
    if (users==null){
      return ResultFactory.empty();
    }
    return ResultFactory.ok(users);
  }

  @GetMapping("getone")
  public JsonResult<User> getOneUser(@RequestParam Integer userId){
    User user =userService.getById(userId);
    if (user==null){
      return ResultFactory.empty();
    }
    return ResultFactory.ok(user);
  }


  @PostMapping("createuser")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "file", name = "file", value = "file", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "username", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "password", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "email", value = "email", required = false)})
  public JsonResult createUser(HttpServletRequest request,
                               @RequestParam MultipartFile file,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam(required = false) String role,
                               @RequestParam String email) throws Exception{
    if (username==null||password==null||email==null){
      return ResultFactory.bad();
    }

    //验证码校验
    if (!VerifyCodeUtil.checkVerifyCode(request)) {
      return ResultFactory.notGo();
    }
    User user=new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setRole(role);
    user.setEmail(email);
    user.setCreateTime(LocalDateTime.now());
   Thumb thumb =thumbService.saveThumb(file,null,"user");
   user.setThumbId(thumb.getThumbId());
    if (userService.save(user)) {
      return ResultFactory.ok("用户注册成功!!!");
    }
      return ResultFactory.erro();
  }

  @PostMapping("modify")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "file", name = "file", value = "file", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "userId", value = "userId", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "username", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "password", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "email", value = "email", required = false)})
  public JsonResult modifyUser(HttpServletRequest request,
                               @RequestParam(required = false) MultipartFile file,
                               @RequestParam Integer userId,
                               @RequestParam String username,
                               @RequestParam(required = false) String role,
                               @RequestParam String password,
                               @RequestParam String email) {

    if (username == null || password == null || email == null || userId == null) {
      return ResultFactory.bad();
    }

    //验证码校验
    if (!VerifyCodeUtil.checkVerifyCode(request)) {
      return ResultFactory.notGo();
    }
    User user = new User();
    user.setUserId(userId);
    user.setUsername(username);
    user.setRole(role);
    user.setPassword(password);
    user.setEmail(email);
    user.setLastLoginTime(LocalDateTime.now());
    if (file != null){
      User user1 =userService.getOneUser(userId);
      Thumb thumbTemp =new Thumb();
      thumbTemp.setThumbId(user1.getThumbId());
      Thumb thumb = thumbService.updateThumb(file, thumbTemp, "user");
    user.setThumbId(thumb.getThumbId());
    }
    if (userService.updateById(user)){
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }

  @DeleteMapping("delete")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "userId", value = "userId", required = false)})
  public JsonResult delete(@RequestParam Integer userId){
    //TODO 得到管理员权限

    if (userService.removeById(userId)){
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }

/*
  @PostMapping("login")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "username", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "password", required = false)})
  public JsonResult loginUser(HttpServletRequest request,
                               @RequestParam String username,
                               @RequestParam String password) {
    if (username == null || password == null) {
      return ResultFactory.bad();
    }
    if (!VerifyCodeUtil.checkVerifyCode(request)){
      return ResultFactory.notGo();
    }
    User user = new User();
    user.setUsername(username);
    user.setUsername(password);
    userService.login(user,request);
     return ResultFactory.ok();
  }

*/

}

