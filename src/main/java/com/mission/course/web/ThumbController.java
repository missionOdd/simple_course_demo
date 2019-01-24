package com.mission.course.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Thumb;
import com.mission.course.service.ThumbService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/thumb")
@Transactional
public class ThumbController {


  @Resource
  private ThumbService thumbService;

  @PostMapping("add")
  public JsonResult addThumb(@RequestParam MultipartFile file,
                             @RequestParam String thumbName,
                             @RequestParam String thumbDesc,
                             @RequestParam Integer thumbCategoryId){

 if(file == null|| thumbName == null|| thumbDesc == null|| thumbCategoryId == null){
   return ResultFactory.bad();
 }

    Thumb thumb =new Thumb();
    thumb.setThumbName(thumbName);
    thumb.setThumbDesc(thumbDesc);
    thumb.setThumbCategoryId(thumbCategoryId);

    if (handleSavePhoto(file, thumb)){ return ResultFactory.ok();}
    return ResultFactory.erro();
  }





  @PostMapping("modify")
  public JsonResult modifyThumb(@RequestParam MultipartFile file,
                             @RequestParam Integer thumbId,
                             @RequestParam String thumbName,
                             @RequestParam String thumbDesc,
                             @RequestParam Integer thumbCategoryId){

    if(thumbId==null||file == null|| thumbName == null|| thumbDesc == null|| thumbCategoryId == null){
      return ResultFactory.bad();
    }

    Thumb thumb =new Thumb();
    thumb.setThumbId(thumbId);
    thumb.setThumbName(thumbName);
    thumb.setThumbDesc(thumbDesc);
    thumb.setThumbCategoryId(thumbCategoryId);

    if (handleSavePhoto(file, thumb)){ return ResultFactory.ok();}
    return ResultFactory.erro();
  }


  @GetMapping("getlist")
  public JsonResult getPhoto(@RequestParam Integer pageIndex,
                             @RequestParam Integer pageSize,
                             @RequestParam(required = false)Integer thumbId,
                             @RequestParam(required = false)Integer thumbCategoryId,
                             @RequestParam(required = false)Integer parentId){
    if (pageIndex==null||pageSize==null){
      return ResultFactory.bad();
    }
    Page page =new Page(pageIndex,pageSize);
    Thumb thumb =new Thumb();
    if (thumbId!=null){
      thumb.setThumbId(thumbId);
    }
    if (thumbCategoryId!=null){
      thumb.setThumbCategoryId(thumbCategoryId);
    }
    if (parentId!=null){
      thumb.setParentId(parentId);
    }
    List<Thumb> thumbList= thumbService.getThumbList(page,thumb);
    if (thumbList==null&&thumbList.size()>0){
      return ResultFactory.empty();
    }
    page.setRecords(thumbList);
    return ResultFactory.ok(page,thumbList.size());
  }



  @GetMapping("getone")
  public JsonResult getPhoto(@RequestParam Integer thumbId){
    if (thumbId==null){
      return ResultFactory.bad();
    }
    Thumb thumb =thumbService.getById(thumbId);
    if (thumb==null){
      return ResultFactory.empty();
    }

    return ResultFactory.ok(thumb);
  }

  @DeleteMapping("delete")
  public JsonResult deletePhoto(@RequestParam Integer thumbId){
    if (thumbId==null){
      return ResultFactory.bad();
    }

    Thumb thumb = new Thumb();
    thumb.setThumbId(thumbId);
    Thumb thumbTemp=thumbService.getById(thumbId);
    if (thumbTemp!=null&&thumbTemp.getParentId()!=null){
      Thumb parent=new Thumb();
      parent.setThumbId(thumbTemp.getParentId());
     thumbService.deleteThumb(parent);
    }
    if (thumbService.deleteThumb(thumb)) {
      return ResultFactory.ok();

    }
    return ResultFactory.erro();
  }



  private boolean handleSavePhoto(MultipartFile file, Thumb thumb) {
    Thumb photoNew = thumbService.savePhoto(file, thumb, "album");
    if (photoNew != null) {
      thumb.setParentId(photoNew.getThumbId());
      Thumb thumbNew = thumbService.saveThumb(file, thumb, "album");
      if (thumbNew != null) {
        return true;
      }
    }
    return false;
  }

}

