package com.mission.course.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.File;
import com.mission.course.entity.FileCategory;
import com.mission.course.entity.FileType;
import com.mission.course.entity.Thumb;
import com.mission.course.service.FileCategoryService;
import com.mission.course.service.FileService;
import com.mission.course.service.FileTypeService;
import com.mission.course.service.ThumbService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/file")
@Slf4j
public class FileController {

  @Resource
  private FileService fileService;

  @Resource
  private FileCategoryService fileCategoryService;

  @Resource
  private FileTypeService fileTypeService;

  @Resource
  private ThumbService thumbService;



 @PostMapping("upload")
 @ApiImplicitParams({@ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileCategoryId", value = "fileCategoryId", required = false),
     @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileTypeId", value = "fileTypeId", required = false),
     @ApiImplicitParam(paramType = "formData", dataType = "String", name = "fileName", value = "fileName", required = false),
     @ApiImplicitParam(paramType = "formData", dataType = "String", name = "fileTitle", value = "fileTitle", required = false),
     @ApiImplicitParam(paramType = "formData", dataType = "file", name = "files", value = "files", required = false)})
  public JsonResult uploadFile(@RequestParam MultipartFile[] files,
                               @RequestParam String fileName,
                               @RequestParam String fileTitle,
                               @RequestParam String fileDesc,
                               @RequestParam Integer fileCategoryId,
                               @RequestParam Integer fileTypeId){

   File file = new File();
   file.setFileName(fileName);
   file.setFileTitle(fileTitle);
   file.setFileDesc(fileDesc);
   file.setFileCategoryId(fileCategoryId);
   file.setFileTypeId(fileTypeId);
   log.info("files:{}",files.length);
   File tempFile =fileService.saveFile(files,file,"file");
   if (tempFile==null){
     return ResultFactory.erro("保存失败");
   }
   return ResultFactory.ok();
  }


  @GetMapping("getlist")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileCategoryId", value = "fileCategoryId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileTypeId", value = "fileTypeId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "pageIndex", value = "pageIndex", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "pageSize", value = "pageSize", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "fileTitle", value = "fileTitle", required = false)})
  public JsonResult getFileList(@RequestParam(required=false) Integer pageIndex,
                                @RequestParam(required=false) Integer pageSize,
                                @RequestParam(required=false) Integer fileCategoryId,
                                @RequestParam(required = false)Integer fileTypeId,
                                @RequestParam(required = false)String fileTitle){
    File file =new File();
    if (fileCategoryId != null) {
      file.setFileCategoryId(fileCategoryId);
    }
    if (fileTypeId!=null){
      file.setFileTypeId(fileTypeId);
    }
    if (fileTitle!=null){
      file.setFileTitle(fileTitle);
    }

    Page page = new Page(pageIndex,pageSize);
    if (pageIndex!=null&&pageSize!=null){
      page.setCurrent(pageIndex);
      page.setSize(pageSize);
    }
    List<File> fileList = fileService.getFileList(page,file);
    if (fileList.size()<=0){
      return ResultFactory.erro("得不到数据");
    }
    page.setRecords(fileList);
    return ResultFactory.ok(page);
  }




  @PostMapping("modify")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileCategoryId", value = "fileCategoryId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileId", value = "fileId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "int32", name = "fileTypeId", value = "fileTypeId", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "fileName", value = "fileName", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "String", name = "fileTitle", value = "fileTitle", required = false),
      @ApiImplicitParam(paramType = "formData", dataType = "file", name = "files", value = "files", required = false)})
  public JsonResult modifyFile(@RequestParam(required = false) MultipartFile[] files,
                               @RequestParam Integer fileId,
                               @RequestParam String fileName,
                               @RequestParam String fileTitle,
                               @RequestParam String fileDesc,
                               @RequestParam Integer fileCategoryId,
                               @RequestParam Integer fileTypeId){

    File file = new File();
    if(fileId==null){
      return ResultFactory.bad();
    }
    file.setFileId(fileId);
    file.setFileName(fileName);
    file.setFileTitle(fileTitle);
    file.setFileDesc(fileDesc);
    file.setFileCategoryId(fileCategoryId);
    file.setFileTypeId(fileTypeId);
    log.info("files:{}",files.length);
    if (files.length==0&&file.updateById()){
      return ResultFactory.ok();
    }else if (files.length>=2) {
      File tempFile = fileService.saveFile(files, file, "file");
      if (tempFile != null) {
        return ResultFactory.ok();
      }
    }
    return ResultFactory.erro("保存失败");
  }

  @GetMapping("getone")
  public JsonResult getFileList(@RequestParam Integer fileId){
   if (fileId==null){
     return ResultFactory.bad();
   }
    File file = fileService.getById(fileId);
   if (file==null){
     return ResultFactory.over();
   }
    Thumb thumb =thumbService.getById(file.getThumbId());
    FileCategory fileCategory =fileCategoryService.getById(file.getFileCategoryId());
    FileType fileType =fileTypeService.getById(file.getFileCategoryId());
    file.setFileCategory(fileCategory);
    file.setFileType(fileType);
    file.setThumb(thumb);
    return ResultFactory.ok(file);
  }


  @DeleteMapping("delete")
  @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int32", name = "fileId", value = "fileId", required = false)})
  public JsonResult deleteFile(@RequestParam Integer fileId){
   if (fileId==null){
     return ResultFactory.bad();
   }
   if (fileService.deteleFile(fileId)) {
      return ResultFactory.ok();
    }
    return ResultFactory.erro();
  }



}

