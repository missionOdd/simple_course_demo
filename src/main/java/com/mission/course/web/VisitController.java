package com.mission.course.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mission.course.common.exception.MyException;
import com.mission.course.common.util.HttpServletRequestUtil;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Visit;
import com.mission.course.service.VisitService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Slf4j
@RestController
@RequestMapping("/visit")
public class VisitController {

  @Resource
  private VisitService visitService;

  @GetMapping("get")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "postId", value = "postId", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "noticeId", value = "noticeId", required = false)})
  public JsonResult getVisit(HttpServletRequest request) throws Exception {
    int count;
    int like;
    Visit visit = new Visit();
    if (handleVisit(request, visit)) {
      return ResultFactory.bad();
    }
    String ip = HttpServletRequestUtil.getIpAddr(request);
    log.debug(ip);
    if (ip != "") {
      visit.setVisitIp(ip);

      int isExist = visit.selectCount(new QueryWrapper<Visit>(visit));
      log.info("isExis ->{}", isExist);
      if (isExist <= 0) {
        visit.setCreateTime(LocalDateTime.now());
        visit.insert();
      }
      Map resultmap=new HashMap();

      try {
        visit.setVisitIp(null);
        visit.setCreateTime(null);
        count = visitService.count(new QueryWrapper<Visit>(visit));
        visit.setVisitLike(1);
        List<Visit> visitList = visitService.list(new QueryWrapper<Visit>(visit));
        like = visitList.size();
        resultmap.put("visit",count);
        resultmap.put("like",like);
      } catch (Exception e) {
        throw new MyException("查询错误");
      }
      return ResultFactory.ok(resultmap, count);
    } else {
      return ResultFactory.erro();
    }
  }



  @PostMapping("like")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "postId", value = "postId", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "noticeId", value = "noticeId", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "isAddVisit", value = "isAddVisit", required = false)})
  public JsonResult visitLike(@RequestParam(required = false) Integer postId,
                              @RequestParam(required = false) Integer noticeId,
                              @RequestParam(required = false) Integer videoId,
                              HttpServletRequest request) throws Exception {
    if(postId == null&& noticeId == null&&videoId==null){
      return ResultFactory.bad();
    }

    Visit visit = new Visit();
    if (postId!=null){
      visit.setPostId(postId);
    }else if (noticeId!=null){
      visit.setNoticeId(noticeId);
    }else if (videoId!=null){
      visit.setVideoId(videoId);
    }

    String ip = HttpServletRequestUtil.getIpAddr(request);
    log.debug(ip);
    if (ip != null) {
      visit.setVisitIp(ip);
      visit.setVisitLike(1);
      int isExist = visit.selectCount(new QueryWrapper<Visit>(visit));
      log.info("isExis ->{}", isExist);
      if (isExist <= 0) {
        visit.setVisitLike(null);
        Visit visitUpdate=new Visit();
        visitUpdate.setVisitLike(1);
        if (visitService.update(visitUpdate,new UpdateWrapper<Visit>(visit))) {
          return ResultFactory.ok();
        }
      }else {
        visit.setVisitLike(null);
        Visit visitUpdate=new Visit();
        visitUpdate.setVisitLike(0);
        if (visitService.update(visitUpdate,new UpdateWrapper<Visit>(visit))) {
          return ResultFactory.ok();
        }
      }

    }
    return ResultFactory.erro();
  }


  private boolean handleVisit(HttpServletRequest request, Visit visit) {
    int postId = HttpServletRequestUtil.getInt(request, "postId");
    if (postId <= 0) {
      int noticeId = HttpServletRequestUtil.getInt(request, "noticeId");
      if (noticeId <= 0) {
        int videoId = HttpServletRequestUtil.getInt(request, "videoId");
        if (videoId<=0){
          return true;
        }else {
          visit.setVideoId(videoId);
        }
      } else {
        visit.setNoticeId(noticeId);
      }
    } else {
      visit.setPostId(postId);
    }
    return false;
  }

}