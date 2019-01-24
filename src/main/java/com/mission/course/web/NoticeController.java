package com.mission.course.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.dto.JsonResult;
import com.mission.course.dto.ResultFactory;
import com.mission.course.entity.Notice;
import com.mission.course.entity.Thumb;
import com.mission.course.entity.User;
import com.mission.course.service.NoticeService;
import com.mission.course.service.ThumbService;
import com.mission.course.service.UserService;
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
@RequestMapping("/notice")
@Transactional
public class NoticeController{

  @Resource
  private NoticeService noticeService;

  @Resource
  private ThumbService thumbService;

  @Resource
  private UserService userService;

  @GetMapping("getlist")
  public JsonResult getNoticeList(@RequestParam Integer pageIndex,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(required = false) Integer authorId,
                                  @RequestParam(required = false) Integer priority){
    Page page = new Page(pageIndex, pageSize);
    Notice notice =new Notice();
    if (authorId!=null) {
      notice.setUserId(authorId);
    }
    if (priority!=null){
      notice.setPriority(priority);
    }
    List<Notice> noticeList = noticeService.getNoticeList(page,notice);

    if (noticeList!=null&&noticeList.size()>0){
      page.setRecords(noticeList);
      return ResultFactory.ok(page,noticeList.size());
    }
    return ResultFactory.erro();
  }


  @GetMapping("getone")
  public JsonResult getOneNotice(@RequestParam Integer noticeId){
    if (noticeId==null){
      return ResultFactory.bad();
    }
    Notice notice=noticeService.getById(noticeId);
    if (notice != null) {
      User user =userService.getOneUser(notice.getUserId());
      if (user!=null){
        notice.setUser(user);
      }
      return ResultFactory.ok(notice);
    }

    return ResultFactory.over();
  }

  @PostMapping("add")
  public JsonResult addNotice(@RequestParam String noticeTitle,
                              @RequestParam String noticeContent,
                              @RequestParam Integer priority,
                              @RequestParam Integer authorId,
                              @RequestParam MultipartFile file){


  if (noticeTitle==null||noticeContent==null||file==null||priority==null){
    return ResultFactory.bad();
  }

    Thumb thumb=thumbService.saveThumb(file,null,"notice");

    if (thumb!=null) {
      Notice notice =new Notice();
      notice.setUserId(authorId);
      notice.setNoticeTitle(noticeTitle);
      notice.setNoticeContent(noticeContent);
      notice.setCreateTime(LocalDateTime.now());
      notice.setLastEditTime(LocalDateTime.now());
      notice.setNoticeStatus(1);
      notice.setThumbId(thumb.getThumbId());
      notice.setPriority(priority);
      if (noticeService.save(notice)) {
        return ResultFactory.ok();
      }
    }
    return ResultFactory.erro();
  }

  @PostMapping("modify")
  public JsonResult modifyNotice(@RequestParam Integer noticeId,
                                 @RequestParam String noticeTitle,
                                 @RequestParam Integer priority,
                                 @RequestParam Integer authorId,
                                 @RequestParam String noticeContent,
                                 @RequestParam(required = false) MultipartFile file){
    //TODO 获取作者
    Integer userId = 1;

    if (noticeTitle==null||noticeContent==null||priority==null){
      return ResultFactory.bad();
    }
    Notice notice =new Notice();
    if (file!=null) {
      Thumb thumb = thumbService.saveThumb(file, null, "notice");
      notice.setThumbId(thumb.getThumbId());
    }

      notice.setNoticeId(noticeId);
      notice.setUserId(authorId);
      notice.setNoticeTitle(noticeTitle);
      notice.setNoticeContent(noticeContent);
      notice.setLastEditTime(LocalDateTime.now());
      notice.setNoticeStatus(1);
      notice.setPriority(priority);
      if (noticeService.updateById(notice)) {
        return ResultFactory.ok();
      }
    return ResultFactory.erro();
  }

  @DeleteMapping("delete")
  public JsonResult deleteNotice(@RequestParam Integer noticeId){
    if (noticeId==null){
      return ResultFactory.bad();
    }
    if (noticeService.removeById(noticeId)){
      return ResultFactory.ok();
    }
    return ResultFactory.over();
  }
}

