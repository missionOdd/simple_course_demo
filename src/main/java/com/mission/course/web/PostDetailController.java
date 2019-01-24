package com.mission.course.web;


import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.PostDetail;
import com.mission.course.service.PostDetailService;
import com.mission.course.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/postDetail")
public class PostDetailController {

  @Resource
  private PostDetailService postDetailService;
  @Resource
  private PostService postService;


  @GetMapping("get")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", dataType = "int32", name = "postId", value = "postId", required = false)})
  public JsonResult getPost(@RequestParam Integer postId){
    if (postId==null){
      return ResultFactory.bad();
    }
   PostDetail postDetail = postDetailService.getPostDetail(postId);
    if (postDetail!=null){
      return ResultFactory.ok(postDetail);
    }
    return ResultFactory.erro();
  }


}

