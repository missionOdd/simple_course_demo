package com.mission.course.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.File;
import com.mission.course.entity.FileCategory;
import com.mission.course.service.FileCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/fileCategory")
@Slf4j
public class FileCategoryController {

  @Autowired
  private FileCategoryService fileCategoryService;

  @PostMapping("/addorupdate")
  public JsonResult addFileCategory(@RequestParam(required = false) Integer fileCategoryId,
                                    @RequestParam String fileCategoryName,
                                    @RequestParam String fileCategoryDesc){

    if (fileCategoryName==""||fileCategoryDesc==""){
      return ResultFactory.bad();
    }
    FileCategory fileCategory = new FileCategory();
    if (fileCategoryId!=null){
      fileCategory.setFileCategoryId(fileCategoryId);
    }
    fileCategory.setFileCategoryStatus(1);
    fileCategory.setFileCategoryName(fileCategoryName);
    fileCategory.setFileCategoryDesc(fileCategoryDesc);
    if (fileCategoryService.saveOrUpdate(fileCategory)){
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }


  @GetMapping("/getlist")
  public JsonResult getallfilecategory(){
    List<FileCategory> list =fileCategoryService.list(null);
    if (list.size()<=0){
      return ResultFactory.empty();
    }else {
      return ResultFactory.ok(list,list.size());
    }
  }

  @GetMapping("/getone")
  public JsonResult getfilecategory(@RequestParam Integer fileCategoryId){
    if (fileCategoryId==null){
      return ResultFactory.bad();
    }
    FileCategory fileCategory=fileCategoryService.getById(fileCategoryId);
    if (fileCategory==null){
      return ResultFactory.empty();
    }else {
      return ResultFactory.ok(fileCategory);
    }
  }

  @DeleteMapping("/delete")
  public JsonResult deleteFileCategory(Integer fileCategoryId) throws Exception {

    if(fileCategoryId<0){
       return ResultFactory.empty();
    }
    File file =new File();
    file.setFileCategoryId(fileCategoryId);
    file.delete(new QueryWrapper(file));
    if(fileCategoryService.removeById(fileCategoryId)){
      return ResultFactory.ok();
    }else {
      return ResultFactory.over();
    }
  }



}

