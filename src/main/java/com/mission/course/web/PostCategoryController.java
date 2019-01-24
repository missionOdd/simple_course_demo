package com.mission.course.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Post;
import com.mission.course.entity.PostCategory;
import com.mission.course.service.PostCategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/postCategory")
public class PostCategoryController {
  @Resource
  private PostCategoryService postCategoryService;

  @GetMapping("getlist")
  public JsonResult getPostCategory(){
   List<PostCategory> postCategoryList = postCategoryService.list(null);
  if (postCategoryList.size()>0){
    return ResultFactory.ok(postCategoryList,postCategoryList.size());
  }
  return  ResultFactory.empty();
  }

  @GetMapping("getone")
  public JsonResult getonePostCategory(@RequestParam Integer postCategoryId){
   PostCategory postCategory = postCategoryService.getById(postCategoryId);
    if (postCategory!=null){
      return ResultFactory.ok(postCategory);
    }
    return  ResultFactory.empty();
  }

  @PostMapping("addorupdate")
  public JsonResult addPostCategory(@RequestParam(required = false) Integer postCategoryId,
                                    @RequestParam String postCategoryName,
                                    @RequestParam String postCategoryDesc){
    if(postCategoryName == null||postCategoryName == ""|| postCategoryDesc == null|| postCategoryDesc ==""){
      return ResultFactory.bad();
    }

    PostCategory postCategory = new PostCategory();
    if (postCategoryId!=null){
      postCategory.setPostCategoryId(postCategoryId);
    }
    postCategory.setPostCategoryName(postCategoryName);
    postCategory.setPostCategoryDesc(postCategoryDesc);
    if (postCategory.insertOrUpdate()) {
      return ResultFactory.ok();
    }
    return ResultFactory.bad();
  }


  @DeleteMapping("delete")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "postCategoryId", value = "postCategoryId", required = false)})
  public JsonResult deletePostCategory(@RequestParam Integer postCategoryId){
    if (postCategoryId==null){
      return ResultFactory.bad();
    }

    Post post =new Post();
    post.setPostCategoryId(postCategoryId);
    post.delete(new QueryWrapper(post));
    if (postCategoryService.removeById(postCategoryId)){
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }
}

