package com.mission.course.web;


import com.mission.course.common.exception.MyException;
import com.mission.course.common.util.ImgUtil;
import com.mission.course.common.util.MyFileUtil;
import com.mission.course.dto.ImageHolder;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Thumb;
import com.mission.course.entity.ThumbCategory;
import com.mission.course.service.ThumbCategoryService;
import com.mission.course.service.ThumbService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-10
 */
@RestController
@RequestMapping("/thumbCategory")
@Transactional
public class ThumbCategoryController {

  @Resource
  private ThumbCategoryService thumbCategoryService;

  @Resource
  private ThumbService thumbService;

  @GetMapping("getlist")
  public JsonResult getThumblistCategory(){
    List<ThumbCategory> thumbCategoryList =thumbCategoryService.list(null);
    if (thumbCategoryList!=null) {
      return ResultFactory.ok(thumbCategoryList, thumbCategoryList.size());
    }
    return ResultFactory.erro();
  }


  @GetMapping("getone")
  public JsonResult getOneThumbCategory(@RequestParam Integer thumbCategoryId){
    ThumbCategory thumbCategory =thumbCategoryService.getById(thumbCategoryId);
    if (thumbCategory!=null) {
      return ResultFactory.ok(thumbCategory);
    }
    return ResultFactory.erro();
  }

  @PostMapping("add")
  public JsonResult addThumbCategory(@RequestParam MultipartFile file,
                                    @RequestParam String thumbCategoryName,
                                     @RequestParam(required = false) String thumbCategoryDesc){
    if(file == null|| thumbCategoryName == null|| thumbCategoryDesc == null){
      ResultFactory.bad();
    }

    ThumbCategory thumbCategory =new ThumbCategory();
    thumbCategory.setThumbCategoryName(thumbCategoryName);
    thumbCategory.setThumbCategoryDesc(thumbCategoryDesc);
    thumbCategory.setThumbCategoryStatus(1);
    try {
      ImageHolder imageHolder = ImgUtil.getImageHolder(file);
     String src = ImgUtil.generateThumbnail(imageHolder,"ThumbCategory");
      thumbCategory.setThumbCategorySrc(src);
    } catch (IOException e) {
      throw new  MyException("保存缩略图错误");
    }


    if(thumbCategoryService.save(thumbCategory)) {
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }

  @PostMapping("modify")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "formData", dataType = "file", name = "file", value = "file", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "thumbCategoryId", value = "thumbCategoryId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "thumbCategoryName", value = "thumbCategoryName", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "thumbCategoryDesc", value = "thumbCategoryDesc", required = false)})
  public JsonResult modifyThumbCategory(@RequestParam(required = false) MultipartFile file,
                                        @RequestParam Integer thumbCategoryId,
                                        @RequestParam(required = false) String thumbCategoryName,
                                        @RequestParam(required = false) String thumbCategoryDesc){
    if(thumbCategoryId == null|| thumbCategoryName == null|| thumbCategoryDesc == null){
      return ResultFactory.bad();
    }


    ThumbCategory thumbCategory =new ThumbCategory();
    thumbCategory.setThumbCategoryId( thumbCategoryId);
    thumbCategory.setThumbCategoryName(thumbCategoryName);
    thumbCategory.setThumbCategoryDesc(thumbCategoryDesc);

    if (file!=null) {
      try {
        ThumbCategory thumbCategory1 =thumbCategoryService.getById(thumbCategoryId);
        if (thumbCategory1!=null&&thumbCategory1.getThumbCategorySrc()!=null) {
          MyFileUtil.deleteFileOrPath(thumbCategory1.getThumbCategorySrc());
        }
        ImageHolder imageHolder = ImgUtil.getImageHolder(file);
        String src = ImgUtil.generateThumbnail(imageHolder, "ThumbCategory");
        thumbCategory.setThumbCategorySrc(src);
      } catch (IOException e) {
        throw new MyException("保存缩略图错误");
      }
    }


    if (thumbCategoryService.updateById(thumbCategory)) {
      return ResultFactory.ok();
    }
    return ResultFactory.erro("更新失败");
  }

  @DeleteMapping("delete")
  @Transactional
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "thumbCategoryId", value = "thumbCategoryId", required = false)})
  public JsonResult deleteThumbCategory(@RequestParam Integer thumbCategoryId){

    if (thumbCategoryId==null){
      return ResultFactory.bad();
    }
    Thumb thumb =new Thumb();
    thumb.setThumbCategoryId(thumbCategoryId);
     thumbService.deleteThumb(thumb);
     ThumbCategory thumbCategory =thumbCategoryService.getById(thumbCategoryId);
     if (thumbCategory!=null&&thumbCategory.getThumbCategorySrc()!=null) {
       MyFileUtil.deleteFileOrPath(thumbCategory.getThumbCategorySrc());
       if (thumbCategoryService.removeById(thumbCategoryId)) {
         return ResultFactory.ok();
       }
     }
    return ResultFactory.erro();
  }


}

