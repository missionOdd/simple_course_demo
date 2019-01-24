package com.mission.course.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.User;
import com.mission.course.entity.Video;
import com.mission.course.service.UserService;
import com.mission.course.service.VideoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-11
 */
@RestController
@RequestMapping("/video")
public class VideoController {

  @Resource
  private VideoService videoService;

  @Resource
  private UserService userService;

  @GetMapping("getlist")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "pageIndex", value = "pageIndex", required = false),
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "pageSize", value = "pageSize", required = false)})
  public JsonResult getVideo(@RequestParam Integer pageIndex,
                             @RequestParam Integer pageSize,
                             @RequestParam(required = false)Integer userId) {
    if (pageIndex == null || pageSize == null) {
      return ResultFactory.bad();
    }
    Page page = new Page(pageIndex, pageSize);
    Video video =new Video();
    if (userId!=null){
      video.setUserId(userId);
    }
    List<Video> videoList = videoService.getVideoList(page, video);
    if (videoList != null && videoList.size() > 0) {
      page.setRecords(videoList);
      return ResultFactory.ok(page);
      }

    return ResultFactory.erro("获取错误");
  }

  @GetMapping("getone")
  public JsonResult getOneVideo(@RequestParam Integer videoId) {

    if(videoId == null){
      return ResultFactory.bad();
    }
    Video video=videoService.getById(videoId);

    if (video!=null){
      User author =userService.getOneUser(video.getUserId());
      video.setUser(author);
      return ResultFactory.ok(video);
    }
    return ResultFactory.erro("获取错误");
  }


  @PostMapping("add")
  public JsonResult addVideo(@RequestParam MultipartFile[] files,
                             @RequestParam String videoName,
                             @RequestParam String videoDesc){
  //获得userId
    Integer userId =1;

    if (files==null||videoName==null){
      return ResultFactory.bad();
    }

  Video video = new Video();
  video.setUserId(userId);
  video.setVideoName(videoName);
  video.setVideoDesc(videoDesc);
  video.setCreateTime(LocalDateTime.now());
  video.setVideoStatus(1);
  video = videoService.saveVideo(files,video);
  if (video!=null){
    return ResultFactory.ok();
  }
  return ResultFactory.bad();
}

  @PostMapping("modify")
  public JsonResult modifyVideo(@RequestParam(required = false) MultipartFile[] files,
                                @RequestParam Integer videoId,
                                @RequestParam String videoName,
                                @RequestParam String videoDesc){

    if (videoId==null){
      return ResultFactory.bad();
    }

    Video video = new Video();
    video.setVideoId(videoId);
    video.setVideoName(videoName);
    video.setVideoDesc(videoDesc);
    if (files!=null){
      if (files.length!=2){
        return ResultFactory.erro();
      }
      Video videoNew = videoService.updateVideo(files,video);
      if (videoNew!=null){
        return ResultFactory.ok();
      }
    }else if(video.updateById()){
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }

  @DeleteMapping("delete")
  @ApiImplicitParams(@ApiImplicitParam(paramType = "query", dataType = "int32", name = "videoId", value = "videoId", required = false))
  public JsonResult deleteVideo(Integer videoId){
    if (videoId==null){
      return ResultFactory.bad();
    }
    if (videoService.deteleVideo(videoId)){
      return ResultFactory.ok();
    }
    return ResultFactory.bad();
  }

}

