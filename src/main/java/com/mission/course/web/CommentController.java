package com.mission.course.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.common.exception.MyException;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Comment;
import com.mission.course.entity.User;
import com.mission.course.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/comment")
public class CommentController {

  @Resource
  private CommentService commentService;

  @GetMapping("get")
  public JsonResult getComment(@RequestParam(required = false) Integer postId,
                               @RequestParam(required = false) Integer noticeId,
                               @RequestParam(required = false) Integer videoId,
                               @RequestParam Integer pageIndex,
                               @RequestParam Integer pageSize){
    if (pageIndex==null||pageSize==null){
      return ResultFactory.bad();
    }
    Page page = new Page(pageIndex,pageSize);
    Comment comment =new Comment();
    if(postId != null){
      comment.setPostId(postId);
    }else if(noticeId != null){
      comment.setNoticeId(noticeId);
    }else if( videoId != null){
      comment.setVideoId(videoId);
    }else {
      return ResultFactory.bad();
    }


    List<Comment> commentList =commentService.getCommentList(page,comment);
    if (commentList.size()>=0) {
      page.setRecords(commentList);
      return ResultFactory.ok(page);
    }
    return ResultFactory.erro();
  }

  /**
   * 仅限文章
   * @param postId
   * @return
   */
  @GetMapping("getcount")
  public JsonResult getCommentCount(@RequestParam(required = false) Integer postId){
    if (postId==null){
      return ResultFactory.bad();
    }
    Comment comment =new Comment();
    comment.setPostId(postId);
    Integer Count =commentService.count(new QueryWrapper<>(comment).isNull("parent_id"));
    if (Count>=0) {
      return ResultFactory.ok(Count,Count);
    }
    return ResultFactory.erro();
  }


  @PostMapping("add")
  public JsonResult getComment(@RequestParam String messages,
                               @RequestParam(required = false) Integer postId,
                               @RequestParam(required = false) Integer noticeId,
                               @RequestParam(required = false) Integer videoId,
                               @RequestParam(required = false) Integer parentId){
    // 从session获取用户id TODO
    User user;

    try {
      Subject subject = SecurityUtils.getSubject();
      user = (User) subject.getPrincipal();
      if (user==null){
        return ResultFactory.erro();
      }
    } catch (Exception e) {
      throw new MyException("请先登录");
    }

    Integer userId = user.getUserId();

    Integer level;

    if (messages==""){
      return ResultFactory.bad();
    }
    Comment comment =new Comment();
    if(postId != null){
      comment.setPostId(postId);
    }else if(noticeId != null){
      comment.setNoticeId(noticeId);
    }else if( videoId != null){
      comment.setVideoId(videoId);
    }else {
      return ResultFactory.bad();
    }

    if (parentId!=null){
      comment.setParentId(parentId);
      level=commentService.count(new QueryWrapper<>(comment));
      //通知其他用户 TODO
    }else {
      level=commentService.count(new QueryWrapper<>(comment).isNull("parent_id"));
    }
    level++;
    comment.setLevel(level);
    comment.setMessages(messages);
    comment.setCommentStatus(1);
    comment.setUserId(userId);
    comment.setCreateTime(LocalDateTime.now());
    if (commentService.save(comment)){
      return ResultFactory.ok();
    }

    return ResultFactory.erro("数据错误");
  }
}

