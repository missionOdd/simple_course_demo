package com.mission.course.web;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Post;
import com.mission.course.entity.PostCategory;
import com.mission.course.entity.PostDetail;
import com.mission.course.entity.Thumb;
import com.mission.course.service.PostCategoryService;
import com.mission.course.service.PostDetailService;
import com.mission.course.service.PostService;
import com.mission.course.service.ThumbService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.transaction.annotation.Transactional;
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
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/post")
@Transactional
public class PostController {

  @Resource
  private PostService postService;

  @Resource
  private PostDetailService postDetailService;

  @Resource
  private PostCategoryService postCategoryService;

  @Resource
  private ThumbService thumbService;

  @GetMapping("getlist")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "postCategoryId", value = "postCategoryId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "postTitle", value = "postTitle", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "pageIndex", value = "pageIndex", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "pageSize", value = "pageSize", required = false)})
  public JsonResult getPost(@RequestParam(required = false) Integer postCategoryId,
                            @RequestParam(required = false) String postTitle,
                            @RequestParam(required = false) Integer priority,
                            @RequestParam(required = false) Integer authorId,
                            @RequestParam Integer pageIndex,
                            @RequestParam Integer pageSize){

    Post post  = new Post();
    if (postCategoryId!=null){
      post.setPostCategoryId(postCategoryId);
    }
    if (postTitle!=null){
      post.setPostTitle(postTitle);
    }
    if (priority!=null){
      post.setPriority(priority);
    }

    if (authorId!=null){
      post.setUserId(authorId);
    }
    Page page = new Page(pageIndex,pageSize);
   List<Post> postList = postService.getPostList(page,post);
   if (postList!=null&&postList.size()>=0){
     page.setRecords(postList);
     return ResultFactory.ok(page ,postList.size());
   }
  return ResultFactory.erro();
  }



  @GetMapping("getone")
  public JsonResult getOnePost(@RequestParam Integer postId){

    if(postId == null){
      return ResultFactory.bad();
    }

    Post post  = postService.getById(postId);
    if (post!=null){
      Thumb thumb=thumbService.getById(post.getThumbId());
      if (thumb!=null){
        post.setThumb(thumb);
      }
      PostCategory postCategory = postCategoryService.getById(post.getPostCategoryId());
      if (postCategory != null) {
        post.setPostCategory(postCategory);
      }
      return ResultFactory.ok(post);
    }

    return ResultFactory.erro();
  }

  @PostMapping("add")
  public JsonResult addPost(@RequestParam MultipartFile file,
                            @RequestParam Integer priority,
                            @RequestParam String postTitle,
                            @RequestParam String postContent,
                            @RequestParam String postSummary,
                            @RequestParam Integer postCategoryId,
                            @RequestParam Integer authorId
                     ){

    if(file == null|| priority == null|| postTitle == null|| postContent == null|| postCategoryId == null){
      return ResultFactory.bad();
    }


    //TODO 获取用户id
    Integer userId =1;

    Post post = new Post();
    post.setUserId(authorId);
    post.setPostTitle(postTitle);
    post.setPriority(priority);
    post.setPostCategoryId(postCategoryId);
    post.setPostStatus(1);
    post.setLastEditTime(LocalDateTime.now());
    post.setCreateTime(LocalDateTime.now());
    if (postSummary.length()>100) {
      post.setPostSummary(postSummary.substring(0, 200) + "......");
    }
    post.setPostSummary(postSummary);
    post.setVersion(1);

    PostDetail postDetail =new PostDetail();
    postDetail.setUserId(authorId);
    postDetail.setPostContent(postContent);
    postDetail.setVersion(1);

    if (postDetail.insert()) {
      post.setPostId(postDetail.getPostId());

      Thumb thumb=thumbService.saveThumb(file,null,"post");

      if (thumb!=null) {
        post.setThumbId(thumb.getThumbId());
      }
      if(post.insertOrUpdate()){
        return ResultFactory.ok();
      }
    }
    return ResultFactory.erro("保存文章错误");
  }

  @PostMapping("modify")
  public JsonResult modifyPost(@RequestParam(required = false) MultipartFile file,
                               @RequestParam Integer postId,
                               @RequestParam Integer priority,
                               @RequestParam String postTitle,
                               @RequestParam String postContent,
                               @RequestParam String postSummary,
                               @RequestParam Integer postCategoryId,
                               @RequestParam Integer authorId){
    if(postId == null|| priority == null|| postTitle == null|| postContent == null|| postCategoryId == null|| authorId == null){
       return ResultFactory.bad();
    }


    //TODO 获取用户id
    Integer userId =1;

    Post post = new Post();
    post.setUserId(authorId);
    post.setPostId(postId);
    post.setPostTitle(postTitle);
    post.setPriority(priority);
    post.setPostCategoryId(postCategoryId);
    post.setPostStatus(1);
    post.setLastEditTime(LocalDateTime.now());
    if (postSummary.length()>100) {
      post.setPostSummary(postSummary.substring(0, 200) + "......");
    }
    post.setPostSummary(postSummary);

    PostDetail postDetail =new PostDetail();
    postDetail.setPostId(postId);
    postDetail.setUserId(authorId);
    postDetail.setPostContent(postContent);


    if (postDetail.updateById()) {

      if (file!=null) {
        Post post1 =post.selectById();
        Thumb thumb1 = new Thumb();
        thumb1.setThumbId(post1.getThumbId());

        Thumb thumb = thumbService.updateThumb(file, thumb1, "post");
        if (thumb != null) {
          post.setThumbId(thumb.getThumbId());
        }
      }


      if(post.updateById()){
        return ResultFactory.ok();
      }
    }
    return ResultFactory.erro("保存文章错误");
  }

  @DeleteMapping("delete")
  public JsonResult deletePost(@RequestParam Integer postId){
    if (postId==null){
      ResultFactory.bad();
    }

    //TODO 获取用户权限

    Post post =postService.getById(postId);
    if (post!=null){
      Thumb thumb =new Thumb();
      thumb.setThumbId(post.getThumbId());
      thumbService.deleteThumb(thumb);
      if(postDetailService.removeById(postId)||
      postService.removeById(postId)){
        return ResultFactory.ok();
      }
    }
    return ResultFactory.erro();
  }

}

