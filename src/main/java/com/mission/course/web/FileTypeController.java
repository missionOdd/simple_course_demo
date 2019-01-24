package com.mission.course.web;


import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.FileType;
import com.mission.course.service.FileTypeService;
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
@RequestMapping("/fileType")
public class FileTypeController {

  @Resource
  FileTypeService fileTypeService;

  @GetMapping("getlist")
  public JsonResult getFileTypeList() {
    List<FileType> fileTypeList = fileTypeService.list(null);
    if (fileTypeList.size() <= 0) {
      return ResultFactory.empty();
    }
    return ResultFactory.ok(fileTypeList, fileTypeList.size());
  }

  @PostMapping("add")
  public JsonResult addFileType(@RequestBody FileType fileType){
    if (fileType==null){
      return ResultFactory.bad();
    }
    if (fileType.insert()) {
      return ResultFactory.ok();
    }
    return ResultFactory.erro("无法添加");
  }

  @PutMapping("modify")
  public JsonResult modifFileType(@RequestBody FileType fileType){
    if (fileType==null||fileType.getFileTypeId()==null){
      return ResultFactory.bad();
    }
    if (fileType.updateById()){
      return ResultFactory.ok();
    }
    return ResultFactory.erro("更新失败");
  }

  @DeleteMapping("delete")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileTypeId", value = "fileTypeId", required = false)})
  public JsonResult deleteFileType(@RequestParam Integer fileTypeId){
    if (fileTypeId==null){
      return ResultFactory.bad();
    }else{
      fileTypeService.removeById(fileTypeId);
      return ResultFactory.ok();
    }
  }
}

