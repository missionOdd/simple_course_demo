package com.mission.course.web;


import com.mission.course.common.util.MyFileUtil;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Site;
import com.mission.course.entity.Thumb;
import com.mission.course.service.SiteService;
import com.mission.course.service.ThumbService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/site")
public class SiteController {

  @Resource
  private SiteService siteService;


  @Resource
  private ThumbService thumbService;

  @GetMapping("get")
  public JsonResult<Site> getSite()throws Exception{
    Site site=siteService.selectSiteById(1);
  if (site==null){
    return ResultFactory.empty();
  }
  return ResultFactory.ok(site);
  }

  @PutMapping("modify")
  public JsonResult modifySite(String siteTitle,String headline, @RequestParam(value = "file",required = false) MultipartFile file)throws Exception {
    if (siteTitle == ""&&headline=="") {
      return ResultFactory.bad();
    }

    Site site =new Site();
    site.setSiteId(1);
    Site site1=site.selectById();

    if (file!=null){
      Thumb thumb =thumbService.savePhoto(file,null,"site");
      MyFileUtil.deleteFileOrPath(site1.getHeadlineImg());
      site.setThumbId(thumb.getThumbId());
      site.setHeadlineImg(thumb.getThumbSrc());
    }

    site.setHeadline(headline);
    site.setSiteTitle(siteTitle);
    site.setSiteId(1);
    site.setCreateTime(LocalDateTime.now());

    if (site.updateById()){
      return ResultFactory.ok();
    }else {
      return ResultFactory.erro();
    }
  }
}

